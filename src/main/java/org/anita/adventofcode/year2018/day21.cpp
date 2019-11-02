#include <iostream>
#include <set>

using namespace std;

int main() {
    set<int> seen;
    int last_r4 = 0;
    int r0, r1, r3, r4, r5;

    r4 = 123;

    l1:
    r4 = r4 & 456;
    r4 = r4 == 72 ? 1 : 0;
    if (r4 == 0) goto l1;
    //  ip = r4 + ip;
    //  ip = 0;

    r4 = 0;

    l6:
    r3 = r4 | 65536;
    r4 = 10283511;

    l8:
    r1 = r3 & 255;
    r4 = r4 + r1;
    r4 = r4 & 16777215;
    r4 = r4 * 65899;
    r4 = r4 & 16777215;
    r1 = 256 > r3 ? 1 : 0;
    if (r1 == 1) goto l28;
    //  ip = r1 + ip;
    //  ip = ip + 1;
    //  ip = 27;

    r1 = 0;
    l18:
    r5 = r1 + 1;
    r5 = r5 * 256;
    r5 = r5 > r3 ? 1 : 0;
    if (r5 == 1) goto l26;
    //  ip = r5 + ip;
    //  ip = ip + 1;
    //  ip = 25;
    r1 = r1 + 1;
    goto l18;
    //  ip = 17;

    l26:
    r3 = r1;
    goto l8;
    //ip = 7;

    l28:
    if (last_r4 == 0) {
        cout << "Part1: " << r4 << endl;
    }
    if (seen.find(r4) == seen.end()) {
        seen.insert(r4);
        last_r4 = r4;
    } else {
        cout << "Part2: " << last_r4 << endl;
        return 0;
    }
    r1 = r4 == r0 ? 1 : 0;
    if (r1 == 0) goto l6;
    //  ip = r1 + ip;
    //  ip = 5;
}