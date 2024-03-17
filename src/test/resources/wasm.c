#include "wasm.h"
#include <string.h>
#include <malloc.h>

int add(int x, int y) {
    return x + y;
}

char* concat_str(char *s1, char *s2) {
    char *ret = (char *)malloc((strlen(s1) + strlen(s2)) * sizeof(char));
    strcat(ret, s1);
    strcat(ret, s2);
    return ret;
}

/*

emcc wasm.c -s "EXPORTED_FUNCTIONS=['_add', '_concat_str']"  -O

*/