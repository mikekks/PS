#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

#define CMD_INIT 100
#define CMD_DESTROY 200
#define CMD_ALLY 300
#define CMD_ATTACK 400
#define CMD_RECRUIT 500

/////////////////////////////////////////////////////////////////////////
extern void init(int N, int mSoldier[25][25], char mGeneral[25][25][11]);
extern void destroy();
extern int ally(char mMonarchA[11], char mMonarchB[11]);
extern int attack(char mMonarchA[11], char mMonarchB[11], char mGeneral[11]);
extern int recruit(char mMonarch[11], int mNum, int mOption);
/////////////////////////////////////////////////////////////////////////

#define MAX_N 25
#define MAX_L 10

static int Sol[MAX_N][MAX_N];
static char General[MAX_N][MAX_N][MAX_L + 1];

static int run()
{
    int isOK = 0;

    int N;
    int cmd;
    int result;
    int check;

    int mN;
    char mMonarchA[11];
    char mMonarchB[11];
    char mGeneral[11];
    int mOption;
    int num;

    scanf("%d", &N);

    for (int c = 0; c < N; ++c)
    {
        scanf("%d", &cmd);
        switch (cmd)
        {
        case CMD_INIT:
            scanf("%d", &mN);
            for (int j = 0; j < mN; j++)
                for (int i = 0; i < mN; i++)
                    scanf("%d", &Sol[j][i]);

            for (int j = 0; j < mN; j++)
                for (int i = 0; i < mN; i++)
                    scanf("%s", General[j][i]);

            init(mN, Sol, General);
            isOK = 1;
            break;

        case CMD_ALLY:
            scanf("%s %s", mMonarchA, mMonarchB);
            result = ally(mMonarchA, mMonarchB);
            scanf("%d", &check);
            if (result != check)
                isOK = 0;
            break;

        case CMD_ATTACK:
            scanf("%s %s %s", mMonarchA, mMonarchB, mGeneral);
            result = attack(mMonarchA, mMonarchB, mGeneral);
            scanf("%d", &check);
            if (result != check)
                isOK = 0;
            break;

        case CMD_RECRUIT:
            scanf("%s %d %d", mMonarchA, &num, &mOption);
            result = recruit(mMonarchA, num, mOption);
            scanf("%d", &check);
            if (result != check)
                isOK = 0;
            break;

        default:
            isOK = 0;
            break;
        }
    }

    destroy();

    return isOK;
}

int main()
{
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input89.txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++)
    {
        if (run()) printf("#%d %d\n", tc, MARK);
        else printf("#%d %d\n", tc, 0);
    }
    return 0;
}


#include <vector>
#include <list>
#include <cstring>
#include <string>
#include <unordered_map>

#define MAX 8626

using namespace std;

int P[MAX];
list<int> allyList[625];
list<int> enemyList[625];

struct area{
    char name[11];
    int soldier;
    int x;
    int y;
};


int Q;
int cnt;
area areas[MAX];
int areasIdx[26][26];

unordered_map<string, int> NationMap;

int Find(int x){
    if(x == P[x]) return x;
    else return P[x] = Find(P[x]);
}

void Union(int x, int y){
    x = Find(x);
    y = Find(y);
    
    if(x != y){  // 합칠 수 있는 경우
        P[y] = x;
        allyList[x].splice(allyList[x].end(), allyList[y]);
        enemyList[x].splice(enemyList[x].end(), enemyList[y]);
    }
}



void init(int N, int mSoldier[25][25], char mMonarch[25][25][11])
{
    Q = N;
    cnt = N*N;
    NationMap.clear();
    int idx = 0;
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            NationMap[mMonarch[i][j]] = idx;
            areasIdx[i][j] = idx;
            
            areas[idx].soldier = mSoldier[i][j];
            areas[idx].y = i;
            areas[idx].x = j;
            P[idx] = idx;
            
            allyList[idx].clear();
            enemyList[idx].clear();
            
            allyList[idx].push_back(idx);
            idx++;
        }
    }
}

void destroy()
{

}

int ally(char mMonarchA[11], char mMonarchB[11])
{
    int a = NationMap[mMonarchA];
    int b = NationMap[mMonarchB];
    
    int ra = Find(a);
    int rb = Find(b);
    
    // 동일하거나, 이미 동맹 관계인지 체크
    if(strcmp(mMonarchA, mMonarchB) == 0 || ra == rb) return -1;
    
    // 동맹 간에 적대관계가 있는지 체크
    for(auto iter : enemyList[ra]){
        if(Find(iter) == rb) return -2;
    }
    
    Union(a, b);
    
    return 1;
}

int attack(char mMonarchA[11], char mMonarchB[11], char mGeneral[11])
{
    int a = NationMap[mMonarchA];
    int b = NationMap[mMonarchB];
    
    int ra = Find(a);
    int rb = Find(b);
    
    if(ra == rb) return -1;
    
//    bool check = false;
//    if(abs(areas[ra].y - areas[b].y) || abs(areas[ra].x - areas[b].x)) check = true;
//
//    for(auto iter : allyList[ra]){
//        if(abs(areas[iter].y - areas[b].y) || abs(areas[iter].x - areas[b].x)) check = true;
//    }
//    if(!check) return -2;
    
    int sx = areas[b].x - 1;
    int ex = areas[b].x + 1;
    int sy = areas[b].y - 1;
    int ey = areas[b].y + 1;
    
    if(sx < 0) sx = 0;
    if(ex > Q-1) ex = Q-1;
    if(sy < 0) sy = 0;
    if(ey > Q-1) ey = Q-1;
    
    bool check = false;
    for(int i=sy; i<=ey; i++){
        for(int j=sx; j<=ex; j++){
            if(ra == Find(areasIdx[i][j])){
                check = true;
                break;
            }
        }
    }
    if(!check) return -2;
    
    // 전투발생
    
    // 적대 관계 확인 -> 적대 관계 형성
    bool check2 = false;
    for(auto iter : enemyList[ra]){
        if(Find(iter) == rb) check2 = true;
    }
    //if(!check2) return 0;
    
    enemyList[ra].push_back(rb);
    enemyList[rb].push_back(ra);
    
    // 근접 군주 각각 병사 절반 보내 공격
    int totalA = 0;
    int totalB = areas[b].soldier;
    areas[b].soldier = 0;
    
    for(int i=sy; i<=ey; i++){
        for(int j=sx; j<=ex; j++){  // 공격 경우와 방어 경우
            if(ra == Find(areasIdx[i][j])){
                int tmp = areas[areasIdx[i][j]].soldier / 2;
                areas[areasIdx[i][j]].soldier -= tmp;
                totalA += tmp;
            }
            else if(rb == Find(areasIdx[i][j])){
                int tmp = areas[areasIdx[i][j]].soldier / 2;
                areas[areasIdx[i][j]].soldier -= tmp;
                totalB += tmp;
            }
        }
    }
    
    
    // 전투 결괴 - 공격 승리
    if(totalA > totalB){
        allyList[rb].remove(b);
        areas[b].soldier = totalA - totalB;
        areas[cnt] = areas[b];
        areasIdx[areas[cnt].y][areas[cnt].x] = cnt;
        NationMap[mGeneral] = cnt;
        
        P[cnt] = ra;
        allyList[ra].push_back(cnt);
        
        cnt++;
        return 1;
    }
    else{  // 전투 결과 - 방어 승리
        areas[b].soldier = totalB - totalA;
        return 0;
    }
    
}

int recruit(char mMonarch[11], int mNum, int mOption)
{
    if(mOption == 0){
        int cur = NationMap[mMonarch];
        areas[cur].soldier += mNum;
        return areas[cur].soldier;
    }
    else{
        int cur = NationMap[mMonarch];
        int rc = Find(cur);
        int ans = 0;
        int _max = allyList[rc].size();
        int i = 0;
        for(auto iter : allyList[rc]){
            if(i == _max) break;
            areas[iter].soldier += mNum;
            ans += areas[iter].soldier;
            i++;
        }
        return ans;
    }
}

