#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>

#define UP 1
#define DOWN 2
#define LEFT 4
#define RIGHT 5

using namespace std;

int Map[14][14];
int dpY[14][14];
int dpX[14][14];
int N;
int resultCost;
int taken;

vector<pair<int, int>> cell;
vector<int> takeList;

bool cell_check(int y, int x, int take){
    if(take == UP){
        return dpX[x][y] == 1;
    }
    else if(take == DOWN){
        return dpX[x][N] - dpX[x][y] == 0;
    }
    else if(take == LEFT){
        return dpY[y][x] == 1;
    }
    else {
        return dpY[y][N] - dpY[y][x] == 0;
    }
}

pair<pair<int, int>, pair<int, int>> range_cal(int y, int x, int take){
    if(take == UP){
        return {{1,y},{x,0}};
    }
    else if(take == DOWN){
        return {{y,N},{x,0}};
    }
    else if(take == LEFT){
        return {{y,0},{1,x}};
    }
    else {
        return {{y,0},{x,N}};
    }
}

bool cross_check(int y, int x, int take){
    pair<pair<int, int>, pair<int, int>> curRange = range_cal(y, x, take);
    bool check = true;
    
    for(int i=0; i<takeList.size(); i++){
        bool tmpCheck = false;
        int prevTake = takeList[i];
        int prevY = cell[i].first;
        int prevX = cell[i].second;
        pair<pair<int, int>, pair<int, int>> prevRange = range_cal(prevY, prevX, prevTake);
        
        if(curRange.first.second == 0 && prevRange.first.second == 0){  // x평행인 경우
            if(curRange.first.first != prevRange.first.first){
                tmpCheck = true;
            }
            else{
                if(curRange.second.first > prevRange.second.second || curRange.second.second < prevRange.second.first){
                    tmpCheck = true;
                }
            }
        }
        else if(curRange.first.second == 0){  // cur : LR, prev : UD인 경우
            if(curRange.first.first > prevRange.first.second || curRange.first.first < prevRange.first.first){
                tmpCheck = true;
            }
            else{
                if(curRange.second.first > prevRange.second.first || curRange.second.second < prevRange.second.first){
                    tmpCheck = true;
                }
            }
        }
        else if(prevRange.first.second == 0){  // cur : UD, prev : LR인 경우
            if(curRange.first.first > prevRange.first.first || curRange.first.second < prevRange.first.first){
                tmpCheck = true;
            }
            else{
                if(curRange.second.first < prevRange.second.first || curRange.second.first > prevRange.second.second){
                    tmpCheck = true;
                }
            }
        }
        else{  // y평행인 경우
            if(curRange.second.first != prevRange.second.first){
                tmpCheck = true;
            }
            else{
                if(curRange.first.first > prevRange.first.second || curRange.first.second < prevRange.first.first){
                    tmpCheck = true;
                }
            }
        }
        check &= tmpCheck;
    }
    return check;
}
void dfs(int n, int curTaken, int cost){

    if(n == cell.size()){
        if(curTaken > taken){
            resultCost = cost;
            taken = curTaken;
        }else if(curTaken == taken){
            resultCost = min(cost, resultCost);
        }
        return;
    }

    int cy = cell[n].first;
    int cx = cell[n].second;

    if(cy == 1 || cy == N || cx == 1 || cx == N){  // 가장자리 위치한 경우
        // 4경우 확인
        if(cy == 1){
            takeList.push_back(1);
            dfs(n+1, curTaken+1, cost);
            takeList.pop_back();
        }
        if(cy == N){
            takeList.push_back(2);
            dfs(n+1, curTaken+1, cost);
            takeList.pop_back();
        }
        if(cx == 1){
            takeList.push_back(4);
            dfs(n+1, curTaken+1, cost);
            takeList.pop_back();
        }
        if(cx == N){
            takeList.push_back(5);
            dfs(n+1, curTaken+1, cost);
            takeList.pop_back();
        }
        
        dfs(n+1, curTaken+1, cost);
    }
    else{
        pair<int, int> arrow[5] = {{1, cx}, {N, cx}, {0,0}, {cy, 1}, {cy, N}};
        
        for(int i=1; i<=5; i++){ // 4방향 확인, 겹치는지
            if(i == 3)
                continue;
            
            if(cell_check(cy, cx, i)){ // 1. 해당 경로에 다른 셀이 없는지
                if(cross_check(cy, cx, i)){
                    takeList.push_back(i);
                    int c1 = abs(cy-arrow[i-1].first);
                    int c2 = abs(cx-arrow[i-1].second);
                    dfs(n+1, curTaken+1, cost+c1+c2);
                    takeList.pop_back();
                }
            }
            
        }
        dfs(n+1, curTaken, cost);  // 못 넣는 경우
    }

}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int test_case;
    int T;
    
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        memset(Map, '\0', sizeof(Map));
        memset(dpX, '\0', sizeof(dpX));
        memset(dpY, '\0', sizeof(dpY));
        cell.clear();
        takeList.clear();
        
        resultCost = 0;
        taken = 0;
        cin >> N;
        
        memset(Map, '\0', sizeof(Map));
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                cin >> Map[i][j];
                dpY[i][j] = Map[i][j] + dpY[i][j-1];
                dpX[j][i] = Map[i][j] + dpX[j][i-1];
                
                if(Map[i][j] == 1)
                    cell.push_back({i,j});
            }
        }
        
        
        dfs(0,0,0);
        
        
        cout << "#" << test_case << " " << resultCost << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
