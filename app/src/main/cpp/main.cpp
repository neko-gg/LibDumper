#include <cstdio>
#include <cstdlib>
#include <sys/mman.h>
#include <cerrno>
#include "fixer/fix.h"

int main(int argc, char *argv[]) {
    if (argc < 4) {
        printf("<src_so_path> <base_addr_in_memory_in_hex> <out_so_path>\n");
        return -1;
    }
    const char *openPath = argv[1];
    uint64_t base = strtoull(argv[2], 0, 16);
    const char *outPutPath = argv[3];
    fix_so(openPath, outPutPath, base);
}