#include <string>
#include <vector>

using namespace std;

bool isVisited[205];
int N;

void dfs(int cur, vector<vector<int>> computers){
    for(int i=0; i<N; i++){
        if(i == cur){
            continue;
        }
        
        if(computers[cur][i] == 1 && !isVisited[i]){
            isVisited[i] = 1;
            dfs(i, computers);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    N = n;
    
    for(int i=0; i<N; i++){
        if(isVisited[i]){
            continue;
        }
        isVisited[i] = 1;
        dfs(i, computers);
        answer++;
    }
    
    return answer;
}
