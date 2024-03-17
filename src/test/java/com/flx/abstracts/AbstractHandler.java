package com.flx.abstracts;

public abstract class AbstractHandler {

    public interface Call<A extends ReqBean, B extends RespBean> {

        void run(A req, B resp);

        void success(A req, B resp);

        void failure(A req, B resp);

        void exception(A req, B resp);
    }

    public abstract static class AbstractCall<A extends ReqBean, B extends RespBean> implements Call<A, B> {
        @Override
        public void failure(A req, B resp) {
            System.out.println("reqNum:" + req.getReqNum() + ", respNum:" + resp.getRespNum());
        }
    }

    public static <A extends ReqBean, B extends RespBean> void callHandler(Call<A, B> call, A req, B resp) {
        try {
            if (req.getReqNum() == 1) {
                resp.setRespNum(req.getReqNum() * 5);
                call.success(req, resp);
            } else {
                call.failure(req, resp);
            }
        } catch (Exception ex) {
            call.exception(req, resp);
        } finally {
            System.out.println("finish...");
        }
    }

    public static void main(String[] args) {
        ReqBean req = ReqBean.builder().reqNum(1).build();
        RespBean resq = RespBean.builder().build();

        callHandler(new AbstractCall<ReqBean, RespBean>() {
            @Override
            public void run(ReqBean req, RespBean resp) {
                System.out.println("run, reqNum:" + req.getReqNum());
            }

            @Override
            public void success(ReqBean req, RespBean resp) {
                System.out.println("success, reqNum:" + req.getReqNum() + ", respNum:" + resp.getRespNum());
            }

            @Override
            public void exception(ReqBean req, RespBean resp) {
                System.out.println("exception, reqNum:" + req.getReqNum() + ", respNum:" + resp.getRespNum());
            }
        }, req, resq);
    }

}
