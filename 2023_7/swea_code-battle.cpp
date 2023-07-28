#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

#define CMD_INIT 1
#define CMD_HIRE 2
#define CMD_FIRE 3
#define CMD_UPDATE_SOLDIER 4
#define CMD_UPDATE_TEAM 5
#define CMD_BEST_SOLDIER 6

extern void init();
extern void hire(int mID, int mTeam, int mScore);
extern void fire(int mID);
extern void updateSoldier(int mID, int mScore);
extern void updateTeam(int mTeam, int mChangeScore);
extern int bestSoldier(int mTeam);

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

static bool run()
{
    int numQuery;

    int mID, mTeam, mScore, mChangeScore;

    int userAns, ans;

    bool isCorrect = false;

    scanf("%d", &numQuery);

    for (int i = 0; i < numQuery; ++i)
    {
        int cmd;
        scanf("%d", &cmd);
        switch (cmd)
        {
        case CMD_INIT:
            init();
            isCorrect = true;
            break;
        case CMD_HIRE:
            scanf("%d %d %d", &mID, &mTeam, &mScore);
            hire(mID, mTeam, mScore);
            break;
        case CMD_FIRE:
            scanf("%d", &mID);
            fire(mID);
            break;
        case CMD_UPDATE_SOLDIER:
            scanf("%d %d", &mID, &mScore);
            updateSoldier(mID, mScore);
            break;
        case CMD_UPDATE_TEAM:
            scanf("%d %d", &mTeam, &mChangeScore);
            updateTeam(mTeam, mChangeScore);
            break;
        case CMD_BEST_SOLDIER:
            scanf("%d", &mTeam);
            userAns = bestSoldier(mTeam);
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
    //freopen("sample_input.txt", "r", stdin);

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

#define MAX 100001

struct node{
    int mID;
    int mTeam;
    int mScore;
    node* prev;
    node* next;
};

node people[MAX];
node head[6][6];  // team, score
node tail[6][6];  // team, score
int bestId[6][6];

node* createNode(int mID, int mTeam, int mScore){
    node* newNode = &people[mID];
    newNode->mID = mID;
    newNode->mTeam = mTeam;
    newNode->mScore = mScore;
    newNode->prev = nullptr;
    newNode->next = nullptr;
    
    return newNode;
}

void init()
{
    memset(bestId, '\0', sizeof(bestId));
    
    for(int i=1; i<=5; i++){
        for(int j=1; j<=5; j++){
            head[i][j].next = nullptr;
            head[i][j].prev = nullptr;
            head[i][j].mID = -1;
            head[i][j].mTeam = -1;
            head[i][j].mScore = -1;
            
            tail[i][j].next = nullptr;
            tail[i][j].prev = nullptr;
            tail[i][j].mID = -1;
            tail[i][j].mTeam = -1;
            tail[i][j].mScore = -1;
            
            head[i][j].next = &tail[i][j];
            tail[i][j].prev = &head[i][j];
        }
    }
    
    
}

void hire(int mID, int mTeam, int mScore)
{
    node* newNode = createNode(mID, mTeam, mScore);
    
    tail[mTeam][mScore].prev->next = newNode;
    newNode->prev = tail[mTeam][mScore].prev;
    newNode->next = &tail[mTeam][mScore];
    tail[mTeam][mScore].prev = newNode;
    
    if(bestId[mTeam][mScore] < mID)
        bestId[mTeam][mScore] = mID;
}

void fire(int mID)
{
    if(people[mID].prev != nullptr)
        people[mID].prev->next = people[mID].next;
    
    if(people[mID].next != nullptr)
        people[mID].next->prev = people[mID].prev;
    
    if(bestId[people[mID].mTeam][people[mID].mScore] == mID){
        
    }
}

void updateSoldier(int mID, int mScore)
{
    int curTeam = people[mID].mTeam;
    fire(mID);
    hire(mID, curTeam, mScore);
}

void updateTeam(int mTeam, int mChangeScore)
{
    if(mChangeScore == 0) return;
    
    int start, end, iter;
    
    if(mChangeScore > 0){
        start = 5;
        end = 1;
        iter = -1;
    }
    else{
        start = 1;
        end = 5;
        iter = 1;
    }
    
    int cnt = 1;
    
    for(int i=start; cnt<=5; i += iter){
        cnt++;
        if(head[mTeam][i].next->mID == -1)  //현재가 해당 점수의 군인이 없는 경우
            continue;
        
        int nextScore = i+mChangeScore;
        if(nextScore < 1)
            nextScore = 1;
        if(nextScore > 5 )
            nextScore = 5;
        
        if(nextScore == i)
            continue;
        
        head[mTeam][i].next->prev = tail[mTeam][nextScore].prev;
        tail[mTeam][nextScore].prev->next = head[mTeam][i].next;  // 헤드 붙이고
        tail[mTeam][nextScore].prev = tail[mTeam][i].prev;  // 꼬리 붙이기
        tail[mTeam][i].prev->next = &tail[mTeam][nextScore];  // next의 tail과 연결
        
        head[mTeam][i].next = &tail[mTeam][i];  // 이전 tail 초기화
        tail[mTeam][i].prev = &head[mTeam][i];  // 이전 head 초기화
    }
}

int bestSoldier(int mTeam)
{
    int ans = 0;
    
    for(int i=5; i>0; i--){
        if(head[mTeam][i].next->mID == -1)
            continue;
        
        node* iter = head[mTeam][i].next;
        
        while(iter->mID != -1){
            if(iter->mID > ans)
                ans = iter->mID;
            
            iter = iter->next;
        }
        if(ans != 0)
            break;
    }
    return ans;
}
