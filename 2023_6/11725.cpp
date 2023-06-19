#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <queue>
#include <climits>
#include <vector>

#define INF INT_MAX
using namespace std;

int N, M, X;


vector<int> Tree[100001];
queue<pair<int, int> > r;
int result[100001];

int main(void)
{
    scanf("%d", &N);
    
    for(int i=0; i<N-1; i++){
        int n1 = 0, n2 = 0;
        scanf("%d %d", &n1, &n2);
        
        Tree[n1].push_back(n2);
        Tree[n2].push_back(n1);
    }
    
    for(int i=0; i<Tree[1].size(); i++){
        int tmp = Tree[1][i];
        r.push({tmp, 1});
    }
   
    result[1] = 1;
    
    while(!r.empty()){
        int cur = r.front().first;
        int par = r.front().second;
        r.pop();
        
        result[cur] = par;
        
        for(int i=0; i<Tree[cur].size(); i++){
            int tmp = Tree[cur][i];
            if(tmp != 0 && result[tmp] == 0)
                r.push({tmp, cur});
        }
        
    }
    
    for(int i=2; i<=N; i++){
        printf("%d\n", result[i]);
    }

}
