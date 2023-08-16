#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

extern void init(int N, int K);
extern void registerUser(int mTime, int mUID, int mNum, int mGroupIDs[]);
extern int offerNews(int mTime, int mNewsID, int mDelay, int mGroupID);
extern void cancelNews(int mTime, int mNewsID);
extern int checkUser(int mTime, int mUID, int mRetIDs[]);

/////////////////////////////////////////////////////////////////////////

#define INIT    0
#define REGI    1
#define OFFER    2
#define CANCEL    3
#define CHECK    4

static int gids[30];
static int ansids[3];
static int retids[3];
static bool run()
{
    int N, K;
    int cmd, ans, ret;
    int time, num, uid, gid, nid, delay;

    int Q = 0;
    bool okay = false;

    scanf("%d", &Q);
    for (int q = 0; q < Q; ++q)
    {
        scanf("%d", &cmd);
        switch (cmd)
        {
        case INIT:
            scanf("%d %d", &N, &K);
            init(N, K);
            okay = true;
            break;

        case REGI:
            scanf("%d %d %d", &time, &uid, &num);
            for (int m = 0; m < num; m++) {
                scanf("%d", &gids[m]);
            }
            registerUser(time, uid, num, gids);
            break;

        case OFFER:
            scanf("%d %d %d %d %d", &time, &nid, &delay, &gid, &ans);
            ret = offerNews(time, nid, delay, gid);
            if (ans != ret) {
                okay = false;
            }
            break;

        case CANCEL:
            scanf("%d %d", &time, &nid);
            cancelNews(time, nid);
            break;

        case CHECK:
            scanf("%d %d %d", &time, &uid, &ans);
            ret = checkUser(time, uid, retids);

            num = ans;
            if (num > 3) num = 3;
            for (int m = 0; m < num; m++) {
                scanf("%d", &ansids[m]);
            }
            if (ans != ret)    {
                okay = false;
            }
            else {
                for (int m = 0; m < num; m++) {
                    if (ansids[m] != retids[m]) {
                        okay = false;
                    }
                }
            }
            break;

        default:
            okay = false;
        }
    }

    return okay;
}

int main()
{
    setbuf(stdout, NULL);
    freopen("/Users/songmingyu/sample_input816.txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++)
    {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }

    return 0;
}





#include <unordered_map>
#include <vector>
#include <list>
#include <queue>

using namespace::std;

struct News{
    int channel;
    int time;
    int newsId;
    //bool _Out;
    bool cancel;
    
    bool operator() (const News* a, const News* b) const
        {
            if (a->time == b->time)
                return a->newsId > b->newsId;
            return a->time > b->time;
        }
};

unordered_map< int, int > user_info;  // 뉴스 채널 -> 유저들
unordered_map< int, int > news_info;  // mNewsID -> 뉴스 채널
unordered_map< int, int > channel_info;

priority_queue<News*, vector<News*>, News> q;   // 뉴스시각, mNewsID, mChannelID
   

News news_pool[30001];
vector<News*> user_pool[501];
vector<int> channel_pool[501];

int ui, ni, ci;


void init(int N, int K)
{
    while(!q.empty()) q.pop();
    
    user_info.clear();
    news_info.clear();
    channel_info.clear();
    
    ui = 1;
    ni = 1;
    ci = 1;
}

void checkNews(int mTime){
    while(!q.empty()){
        News* tmp_news = q.top();
        if(tmp_news->time > mTime) return;
        q.pop();
        
        if(tmp_news->cancel) continue;
        
        for(int i=0; i<channel_pool[tmp_news->channel].size(); i++){
            user_pool[channel_pool[tmp_news->channel][i]].push_back(tmp_news);
        }
    }
}

void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
{
    // 유저에게 보내지는 뉴스 알림 q에서 확인
    checkNews(mTime);
    
    int cur_ui = 0;
    // 추가
    if(user_info[mUID] == 0){  // 등록 x
        cur_ui = user_info[mUID] = ui++;
        user_pool[cur_ui].clear();
    }
    else
        cur_ui = user_info[mUID];
    
    
    for(int i=0; i<mNum; i++){
        int tmp_ch = mChannelIDs[i];
        int cur_ci;
        if(channel_info[tmp_ch] == 0){
            cur_ci = channel_info[tmp_ch] = ci++;
            channel_pool[cur_ci].clear();
        }
        else
            cur_ci = channel_info[tmp_ch];
        
        channel_pool[cur_ci].push_back(cur_ui);
    }
    
}

int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
{
    // 등록
        // 시각, mNewsID, mChannelID
    news_info[mNewsID] = ni;
    News* tmp_news = &news_pool[ni++];
    tmp_news->time = mTime + mDelay;
    tmp_news->cancel = false;
    tmp_news->channel = channel_info[mChannelID];
    tmp_news->newsId = mNewsID;
   
    q.push(tmp_news);
    int cur = channel_info[mChannelID];
    int ans = (int) channel_pool[cur].size();
    
    return ans;
}

void cancelNews(int mTime, int mNewsID)
{
    // mNewsID로 찾기
    news_pool[news_info[mNewsID]].cancel = true;
}

int checkUser(int mTime, int mUID, int mRetIDs[])
{
    // 먼저 해당 시각에 뉴스 알림 확인
    checkNews(mTime);
    
    int cur_ui = user_info[mUID];
    int ans = (int) user_pool[cur_ui].size();
    int k = 0;
    
    for(int i=(int) user_pool[cur_ui].size() - 1; i >= 0; i--){
        if(user_pool[cur_ui][i]->cancel)
            ans--;
        else if(k<3){
            mRetIDs[k++] = user_pool[cur_ui][i]->newsId;
        }
    }
    user_pool[cur_ui].clear();
    
    return ans;
}
