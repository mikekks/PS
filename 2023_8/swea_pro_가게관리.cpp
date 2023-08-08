#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

extern void init();
extern int buy(int bId, int mProduct, int mPrice, int mQuantity);
extern int cancel(int bId);
extern int sell(int sId, int mProduct, int mPrice, int mQuantity);
extern int refund(int sId);

/////////////////////////////////////////////////////////////////////////

#define CMD_INIT 1
#define CMD_BUY 2
#define CMD_CANCEL 3
#define CMD_SELL 4
#define CMD_REFUND 5

static bool run() {
    int q;
    scanf("%d", &q);

    int id, product, price, quantity;
    int cmd, ans, ret = 0;
    bool okay = false;

    for (int i = 0; i < q; ++i) {
        scanf("%d", &cmd);
        switch (cmd) {
            case CMD_INIT:
                init();
                okay = true;
                break;
            case CMD_BUY:
                scanf("%d %d %d %d %d", &id, &product, &price, &quantity, &ans);
                ret = buy(id, product, price, quantity);
                if (ans != ret)
                    okay = false;
                break;
            case CMD_CANCEL:
                scanf("%d %d", &id, &ans);
                ret = cancel(id);
                if (ans != ret)
                    okay = false;
                break;
            case CMD_SELL:
                scanf("%d %d %d %d %d", &id, &product, &price, &quantity, &ans);
                ret = sell(id, product, price, quantity);
                if (ans != ret)
                    okay = false;
                break;
            case CMD_REFUND:
                scanf("%d %d", &id, &ans);
                ret = refund(id);
                if (ans != ret)
                    okay = false;
                break;
            default:
                okay = false;
                break;
        }
    }
    return okay;
}

int main() {
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input88.txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++) {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }

    return 0;
}



#include <queue>
#include <unordered_map>
#include <vector>

using namespace std;

struct b_item{
    int mProduct;
    int mPrice;
    int mQuantity;
    int mRest;
};

struct s_item{
    int mProduct;
    int mPrice;
    int mQuantity;
    bool isTrue;
    vector<pair<int ,int>> blist;  // bId, 수량
};

unordered_map<int, b_item> buys;
unordered_map<int, int> total;

priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int ,int>>> q[605];  // 구매가격, 구매ID
unordered_map<int, int> q_idx;  // mProduct, q[MAX]에서의 인덱스
int last_idx;

unordered_map<int, s_item> sells;


void init() {
    for(int i=0; i<last_idx; i++){
        while(!q[i].empty()) q[i].pop();
    }
    last_idx = 1;
    buys.clear();
    total.clear();
    q_idx.clear();
    
    
    return;
}

int buy(int bId, int mProduct, int mPrice, int mQuantity) {
    b_item tmp = {mProduct, mPrice, mQuantity, mQuantity};
    buys[bId] = tmp;
    total[mProduct] += mQuantity;
    
    if(q_idx[mProduct] == 0){
        q_idx[mProduct] = last_idx++;
    }
    int t_idx = q_idx[mProduct];
    
    q[t_idx].push({mPrice, bId});
    
    return total[mProduct];
}

int cancel(int bId) {
    // 구매 이력 없거나,
    if(buys.count(bId) == 0){  // 이미 취소했는지 확인?
        return -1;
    }
    
    // 상품 수량 확인
    int mP = buys[bId].mProduct;

    if(buys[bId].mQuantity == buys[bId].mRest){ // 가능
        total[mP] -= buys[bId].mRest;
        buys[bId].mRest = 0;
        return total[mP];
    }else{  // 불가능
        return -1;
    }
    
}

int sell(int sId, int mProduct, int mPrice, int mQuantity) {
    
    if(total[mProduct] < mQuantity){ // 판매 불가
        return -1;
    }
    
    int idx = q_idx[mProduct];
    int cnt = 0;
    int revenue = 0;
    s_item tmp;
    tmp.mProduct = mProduct;
    tmp.mPrice = mPrice;
    tmp.mQuantity = mQuantity;
    tmp.isTrue = true;
    
    while(cnt != mQuantity){
        int curId = q[idx].top().second;
        int curPrice = q[idx].top().first;
        
        if(cnt + buys[curId].mRest >= mQuantity){
            int outQuantity = mQuantity - cnt;
            buys[curId].mRest -= outQuantity;
            cnt += outQuantity;
            total[mProduct] -= outQuantity;
            revenue += outQuantity * (mPrice - buys[curId].mPrice);
            tmp.blist.push_back({curId, outQuantity});
            
            if(buys[curId].mRest == 0){
                q[idx].pop();
            }
        }
        else{
            int outQuantity = buys[curId].mRest;
            buys[curId].mRest = 0;
            cnt += outQuantity;
            total[mProduct] -= outQuantity;
            revenue += outQuantity * (mPrice - buys[curId].mPrice);
            tmp.blist.push_back({curId, outQuantity});
            
            q[idx].pop();
        }
        
    }
    
    sells[sId] = tmp;
    
    return revenue;
}

int refund(int sId) {
    // 판매 이력 없거나,
    if(sells.count(sId) == 0) return -1;
    
    // 이미 취소했는지 확인?
    if(!sells[sId].isTrue) return -1;
    
    int ans = sells[sId].mQuantity;
    sells[sId].isTrue = false;
    // 다시 재고에 넣기 작업
    for(int i=0; i<sells[sId].blist.size(); i++){
        int curBId = sells[sId].blist[i].first;
        int Size = sells[sId].blist[i].second;
        int curPrice = buys[curBId].mPrice;
        int curProduct = buys[curBId].mProduct;
        int idx = q_idx[curProduct];
        
        if(buys[curBId].mRest == 0){
            q[idx].push({curPrice, curBId});
        }
        total[curProduct] += Size;
        buys[curBId].mRest += Size;
    }
    
    
    return ans;
}
