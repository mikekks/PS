#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1<<19
#define INF 987654321
#define MOD 9901

using namespace std;

typedef long long ll;

int T;
int N, M;
int total;
int Map[10][10];
bool fin;
vector<pair<int, int>> v;

bool row[10][10];
bool col[10][10];
bool Square[4][4][10];

void back_track(int idx){
    
    if(idx == total){
        for(int i=1; i<=9; i++){
            for(int j=1; j<=9; j++){
                cout << Map[i][j];
            }
            cout << '\n';
        }
        fin = true;
        return;
    }
    
    int y = v[idx].first;
    int x = v[idx].second;

    
    for(int i=1; i<=9; i++){
        if(!row[y][i] && !col[x][i] && !Square[(y-1)/3+1][(x-1)/3+1][i]){
            Map[y][x] = i;
            row[y][i] = true;
            col[x][i] = true;
            Square[(y-1)/3 + 1][(x-1)/3 + 1][i] = true;
            back_track(idx+1);
            
            if(fin){
                return;
            }
            
            row[y][i] = false;
            col[x][i] = false;
            Square[(y-1)/3 + 1][(x-1)/3 + 1][i] = false;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    
    for(int i=1; i<=9; i++){
        string tmp;
        cin >> tmp;
        for(int j=1; j<=9; j++){
            Map[i][j] = tmp[j-1] - '0';
            row[i][Map[i][j]] = true;
            col[j][Map[i][j]] = true;
            Square[(i-1)/3 + 1][(j-1)/3 + 1][Map[i][j]] = true;
            
            if(Map[i][j] == 0)
                v.push_back({i, j});
        }
    }

    total = v.size();
    
    back_track(0);
    
    
    
    // 최소 비용으로 트리 완전 탐색 -> 다익스트라, 플로이드
    
    
    return 0;
}
