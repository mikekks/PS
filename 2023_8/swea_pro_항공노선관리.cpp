#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

#define CMD_INIT 1
#define CMD_ADD 2
#define CMD_MIN_TRAVEL_TIME 3
#define CMD_MIN_PRICE 4

extern void init(int N);
extern void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice);
extern int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime);
extern int minPrice(int mStartAirport, int mEndAirport);

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

static bool run()
{
    int numQuery;
    int N, mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice;
    int userAns, ans;

    bool isCorrect = false;

    scanf("%d", &numQuery);

    for (int q = 0; q < numQuery; q++)
    {
        int cmd;
        scanf("%d", &cmd);

        switch (cmd)
        {
        case CMD_INIT:
            scanf("%d", &N);
            init(N);
            isCorrect = true;
            break;
        case CMD_ADD:
            scanf("%d %d %d %d %d", &mStartAirport, &mEndAirport, &mStartTime, &mTravelTime, &mPrice);
            add(mStartAirport, mEndAirport, mStartTime, mTravelTime, mPrice);
            break;
        case CMD_MIN_TRAVEL_TIME:
            scanf("%d %d %d", &mStartAirport, &mEndAirport, &mStartTime);
            userAns = minTravelTime(mStartAirport, mEndAirport, mStartTime);
            scanf("%d", &ans);
            if (userAns != ans)
            {
                isCorrect = false;
            }
            break;
        case CMD_MIN_PRICE:
            scanf("%d %d", &mStartAirport, &mEndAirport);
            userAns = minPrice(mStartAirport, mEndAirport);
            scanf("%d", &ans);
            if (userAns != ans)
            {
                isCorrect = false;
            }
            break;
        default:
            isCorrect = false;
            break;
        }
    }
    return isCorrect;
}

int main()
{
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input810.txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++)
    {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }

    return 0;
}







#include <queue>
#include <vector>

#define INF 987654321

using namespace::std;

struct airport{
    int P;  // 도착 위치
    int startTime; // 출발 시간
    int cost;
    int travelTime;  // 걸리는 시간
};

vector<airport> Map[60];  // 도착 위치, 시각
vector<int> cost_Map[60];

//int mTime[60][60];
int mCost[60][60];
int D[60];

void init(int N)
{
    for(int i=0; i<60; i++) Map[i].clear();
    
    for(int i=0; i<60; i++){
        for(int j=0; j<60; j++){
            if(i == j)
                mCost[i][j] = 0;
            else
                mCost[i][j] = INF;
        }
    }
}

void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice)
{
    Map[mStartAirport].push_back({mEndAirport, mStartTime, mPrice, mTravelTime});
    
    if(mCost[mStartAirport][mEndAirport] == INF){ // 새 경로 추기
        cost_Map[mStartAirport].push_back(mEndAirport);
        mCost[mStartAirport][mEndAirport] = mPrice;
    }
    else{
        if(mCost[mStartAirport][mEndAirport] > mPrice)
            mCost[mStartAirport][mEndAirport] = mPrice;
    }
}

int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime)
{
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;  // 걸린 시간, <시각, 현위치>
    
    for(int i=0; i<60; i++) D[i] = INF;
    D[mStartAirport] = 0;
    q.push({0, {mStartTime, mStartAirport}});
    int ans = INF;
    
    while(!q.empty()){
        int curCost = q.top().first;
        int curTime = q.top().second.first;
        int curP = q.top().second.second;
        q.pop();
        
        if(D[curP] < curCost) continue;
        
        if(curP == mEndAirport){
            if(ans > curCost)
                ans = curCost;
        }
        
        if(curCost > ans) continue;
        
        
        for(int i=0; i<Map[curP].size(); i++){
            int nP = Map[curP][i].P;
            int nextCost = Map[curP][i].travelTime;
            int nextStartTime = Map[curP][i].startTime;
            int wTime = 0;
            
            // 다음날인 경우
            if(nextStartTime < curTime){
                wTime = 24 - curTime;
                wTime += nextStartTime;
            }
            else{
                wTime = nextStartTime - curTime;
            }
            int totalCost = wTime + nextCost + curCost;
            
            if(D[nP] > totalCost){
                int nextTime = (curTime + wTime + nextCost) % 24;
                D[nP] = totalCost;
                q.push({totalCost, {nextTime, nP}});
            }
        }
        
    }
    
    if(D[mEndAirport] == INF)
        return -1;
    
    return ans;
}

int minPrice(int mStartAirport, int mEndAirport)
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q;  // 소요 비용, 현위치
    
    for(int i=0; i<60; i++) D[i] = INF;
    D[mStartAirport] = 0;
    q.push({0, mStartAirport});
    int ans = INF;
    
    while(!q.empty()){
        int curCost = q.top().first;
        int curP = q.top().second;
        q.pop();
        
        if(D[curP] < curCost) continue;
        
        if(curP == mEndAirport){
            if(ans > curCost)
                ans = curCost;
        }

        if(curCost > ans) continue;
        
        for(int i=0; i<cost_Map[curP].size(); i++){
            int nP = cost_Map[curP][i];
            int nextCost = mCost[curP][nP];
            int totalCost = curCost + nextCost;
            
            if(D[nP] > totalCost){
                D[nP] = totalCost;
                q.push({totalCost, nP});
            }
        }
        
    }
    
    if(D[mEndAirport] == INF)
        return -1;
    
    return ans;
}
