

#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>
#include <vector>

#define MAX 100001
#define INF 987654321

using namespace std;

int N;
vector<pair<int, int>> Line;
vector<int> ans, idx;
int pos[MAX];

int main(int argc, char** argv)
{
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        int a,b;
        scanf("%d %d", &a, &b);
        Line.push_back({a, b});
    }
    sort(Line.begin(), Line.end());
    ans.push_back(Line[0].second);
    
    for(int i=1; i<N; i++){
        if(ans.back() < Line[i].second){
            ans.push_back(Line[i].second);
            pos[i] = ans.size() - 1;
        }
        else{
            auto it = lower_bound(ans.begin(), ans.end(), Line[i].second);
            *it = Line[i].second;
            pos[i] = it - ans.begin();
        }
    }
    
    int cnt = ans.size();
    
    printf("%d\n", N-cnt);
    cnt--;
    
    for(int i=N-1; i>=0; i--){
        if(pos[i] == cnt){
            cnt--;
            continue;
        }
        idx.push_back(Line[i].first);
    }
    
    for(int i=idx.size()-1; i>=0; i--)
        printf("%d\n", idx[i]);
    
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

