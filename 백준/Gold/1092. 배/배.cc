#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 105

using namespace std;

// 12:04


int map[MAX][MAX];
int visited[MAX][MAX];
int N, M;
int ans = 0;
int cnt = 0;

vector<int> crain;
vector<int> box;

int main(void)
{
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        crain.push_back(tmp);
    }
    
    cin >> M;
    
    for(int i=0; i<M; i++){
        int tmp;
        cin >> tmp;
        box.push_back(tmp);
    }
    
    sort(crain.begin(), crain.end());
    sort(box.begin(), box.end());
    
    int cnt = 0;
    if (crain.back() < box.back()) {
        cout << -1;
        return 0;
    }
    
    while(!box.empty()){
        cnt++;
        for(int i=crain.size()-1; i>=0; i--){
            for(int j=box.size()-1; j>=0; j--){
                if(crain[i] >= box[j]){
                    box.erase(box.begin() + j);
                    break;
                }
            }
        }
    }
    
    cout << cnt << '\n';
    
    
    return 0;
}
