#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 15
#define INF 1000000005

using namespace std;

struct Tree{
    int age;
    int y;
    int x;
    bool isLive;
};

bool compareByAge(const Tree &a, const Tree &b) {
    return a.age < b.age; // 나이를 기준으로 오름차순 정렬
}

int N, M, K;
int A[MAX][MAX];
vector<Tree> trees;
vector<int> aList;
int nutrient[MAX][MAX];
int next_nutrient[MAX][MAX];
int live_cnt;

int ud[3] = {-1,0,1};
int lr[3] = {-1,0,1};

void placeNewTree(int y, int x){
   
}

void process(){
    
    // 봄
    memset(next_nutrient, 0, sizeof(next_nutrient));
    vector<Tree> birthList;
    vector<Tree> dieList;
    
    for(int i=0; i<trees.size(); i++){
        int tAge = trees[i].age;
        int ty = trees[i].y;
        int tx = trees[i].x;
        
        if(nutrient[ty][tx] < tAge){
            next_nutrient[ty][tx] = next_nutrient[ty][tx] + (tAge/2);
            live_cnt--;
        }
        else{
            nutrient[ty][tx] -= tAge;
            birthList.push_back({tAge+1, ty, tx, true});
        }
    }
    
    vector<Tree> newTreeList;
    
    for(int i=0; i<birthList.size(); i++){
        int age = birthList[i].age;
        int y = birthList[i].y;
        int x = birthList[i].x;
        
        if(age % 5 == 0){
            for(int i=0; i<3; i++){
                int ty = y+ud[i];
                
                for(int j=0; j<3; j++){
                    int tx = x+lr[j];
                    
                    if(ty < 1 || tx < 1 || ty > N || tx > N) continue;
                    if(ty == y && tx == x) continue;
                    
                    newTreeList.push_back({1, ty, tx, true});
                    live_cnt++;
                }
            }
        }
    }
    
    copy(birthList.begin(), birthList.end(), back_inserter(newTreeList));
    trees = newTreeList;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            nutrient[i][j] += next_nutrient[i][j];
            nutrient[i][j] += A[i][j];
        }
    }
    
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M >> K;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> A[i][j];
        }
    }
    

    for(int i=0; i<M; i++){
        int x,y,z;
        cin >> x >> y >> z;
        trees.push_back({z, x, y, true});  // x,y 맞는지 검증 필요
        live_cnt++;
    }
    
    sort(trees.begin(), trees.end(), compareByAge);
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            nutrient[i][j] = 5;
        }
    }
    
    for(int i=0; i<K; i++){
        process();
    }
    
    cout << live_cnt << '\n';
    
}
