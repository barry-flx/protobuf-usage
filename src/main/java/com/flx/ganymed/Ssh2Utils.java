package com.flx.ganymed;

import ch.ethz.ssh2.Connection;
import com.flx.utils.Result;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ssh2Utils {

    private static String DEFAULT_CHARSET = "UTF-8";

    public static Connection openConnection(String host, int port, String username, String password) {
        Connection connection;
        try {
            connection = new Connection(host, port);
            if (connection.authenticateWithPassword(username, password)) {
                return connection;
            } else {
                throw new RuntimeException("username or password incorrect");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Result execCommand(Connection connection, String command) {
        Session session = null;
        try {
            session = connection.openSession();
            session.execCommand(command);

            String msg = parseResult(session.getStdout());
            int code = Result.SUCCESS;
            if (StringUtils.isBlank(msg)) {
                code = -1;
                msg = parseResult(session.getStderr());
            }
            return Result.builder().code(code).msg(msg).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Result.builder().code(-1).build();
    }

    /**
     * file download
     *
     * @param connection
     * @param localTargetDirectory local path
     * @param remoteFiles          remote absolute path
     */
    public static void download(Connection connection, String localTargetDirectory, String... remoteFiles) {
        if (remoteFiles == null) {
            return;
        }
        try {
            SCPClient scpClient = connection.createSCPClient();

            scpClient.get(remoteFiles, localTargetDirectory);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * file download by pattern
     *
     * @param connection
     * @param localTargetDirectory local path
     * @param remotetDirectory     remote absolute path
     * @param fileNamePattern      file name pattern
     */
    public static void downloadByPattern(Connection connection, String localTargetDirectory, String remotetDirectory, String fileNamePattern) {
        Result result = execCommand(connection, "ls " + remotetDirectory + "/" + fileNamePattern);
        if (result.success()) {
            String[] files = result.getMsg().split("\n");
            SCPClient scpClient = new SCPClient(connection);
            try {
                scpClient.get(files, localTargetDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * file upload
     *
     * @param connection
     * @param remoteTargetDirectory remote absolute path
     * @param localFiles            local file path
     */
    public static void upload(Connection connection, String remoteTargetDirectory, String... localFiles) {
        if (localFiles == null) {
            return;
        }
        try {
            SCPClient scpClient = connection.createSCPClient();

            scpClient.put(localFiles, remoteTargetDirectory);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String parseResult(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_CHARSET));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
