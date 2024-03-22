#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>
#include <list>

#define MAX 105

using namespace std;

int N, K, L;
bool isApple[MAX][MAX];
char cmd[10005];

int cur_time;
bool isDie;
int dir;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};


struct Snake{
    list<pair<int, int>> sList;
};

Snake snake;

void process(){
    cur_time++;
    
    int ny = snake.sList.front().first + ud[dir];
    int nx = snake.sList.front().second + lr[dir];
    
    // 벽 확인
    if(ny < 1 || ny > N || nx < 1 || nx > N){
        isDie = true;
        return;
    }

    for(pair<int, int> p : snake.sList){
        if(p.first == ny && p.second == nx){
            isDie = true;
            return;
        }
    }
    
    // 머리를 다음 위치로
    snake.sList.push_front({ny, nx});
    
    
    // 사과가 있는지 확인
    if(isApple[ny][nx]){
        isApple[ny][nx] = false;
    }
    else{
        snake.sList.pop_back();
    }
    
    
    // cmd 확인
    if(cmd[cur_time] != 0){
        if(dir == 0){
            dir = cmd[cur_time] == 'L' ? 2 : 3;
        }
        else if(dir == 1){
            dir = cmd[cur_time] == 'L' ? 3 : 2;
        }
        else if(dir == 2){
            dir = cmd[cur_time] == 'L' ? 1 : 0;
        }
        else if(dir == 3){
            dir = cmd[cur_time] == 'L' ? 0 : 1;
        }
        
    }
    
    
}

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    cin >> N;
    cin >> K;
    
    for(int i=0; i<K; i++){
        int y, x;
        cin >> y >> x;
        isApple[y][x] = true;
    }
    
    cin >> L;
    
    for(int i=0; i<L; i++){
        int X;
        char C;
        cin >> X >> C;
        cmd[X] = C;
    }
    
    isDie = false;
    dir = 3;
    snake.sList.push_front({1, 1});
    
    while (!isDie) {
        process();
    }
    
    cout << cur_time << '\n';
}
