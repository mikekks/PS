#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

struct Result
{
    int top;
    int count;
};

void init(int C);
Result dropBlocks(int mCol, int mHeight, int mLength);

#define CMD_INIT 100
#define CMD_DROP 200

static bool run()
{
    int query_num;
    scanf("%d", &query_num);

    int ans_top, ans_count;
    bool ok = false;

    for (int q = 0; q < query_num; q++)
    {
        int query;
        scanf("%d", &query);
        if (query == CMD_INIT)
        {
            int C;
            scanf("%d", &C);
            init(C);
            ok = true;
        }
        else if (query == CMD_DROP)
        {
            int mCol, mHeight, mLength;
            scanf("%d %d %d", &mCol, &mHeight, &mLength);
            Result ret = dropBlocks(mCol, mHeight, mLength);
            scanf("%d %d", &ans_top, &ans_count);
            if (ans_top != ret.top || ans_count != ret.count)
            {
                ok = false;
            }
        }
    }
    return ok;
}

int main()
{
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input818.txt", "r", stdin);
    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++)
    {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }
    return 0;
}




#include <cstring>
#include <algorithm>

#define MAX 1000001
#define MOD 1000000

using namespace::std;

int max_tree[4000000];
int min_tree[4000000];
int Lazy[4000000];

int constC;
int cnt;

struct Result{
    int top;
    int count;
};

void init(int C)
{
    constC = C;
    cnt = 0;
    memset(max_tree, '\0', C * 16);
    memset(min_tree, '\0', C * 16);
    memset(Lazy, '\0', C * 16);
}

void lazy_check(int node, int start, int end){
    if(Lazy[node] != 0){
        min_tree[node] += Lazy[node];
        max_tree[node] += Lazy[node];
        if(start != end){
            Lazy[2*node] += Lazy[node];
            Lazy[2*node+1] += Lazy[node];
        }
        Lazy[node] = 0;
    }
}

void update(int node, int start, int end, int L, int R, int diff){
    lazy_check(node, start, end);
    
    if(start > R || end < L) return;
    
    if(start >= L && end <= R){
        Lazy[node] += diff;
        lazy_check(node, start, end);
        return;
    }
    
    int mid = (start + end) / 2;
    update(2*node, start, mid, L, R, diff);
    update(2*node+1, mid+1, end, L, R, diff);
    min_tree[node] = min(min_tree[2*node], min_tree[2*node+1]);
    max_tree[node] = max(max_tree[2*node], max_tree[2*node+1]);
}

Result dropBlocks(int mCol, int mHeight, int mLength)
{
    Result ret;
    ret.top = 0;
    ret.count = 0;
    
    update(1, 0, constC - 1, mCol, mCol + mLength - 1, mHeight);
    lazy_check(1, 0, constC-1);
    int tmp_min = min_tree[1];
    cnt = ((cnt + mHeight * mLength - tmp_min * constC) % MOD + MOD) % MOD;
    update(1, 0, constC - 1, 0, constC - 1, -tmp_min);
    
    ret.count = cnt;
    lazy_check(1, 0, constC-1);
    ret.top = max_tree[1];
    
    return ret;
}

