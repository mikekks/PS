#include <iostream>
#include <climits>
#define MIN(a,b) a<b ? a:b
#define MAX 101

using namespace std;

int city[MAX][MAX];
int n,m;
int INF = 987654321;

void floyd(){
    int i,j,k;
    
    for(i = 1; i<=n; i++)
        for(j = 1; j<=n; j++)
            for(k = 1; k<=n; k++)
                city[j][k] = MIN(city[j][k], city[j][i] + city[i][k]);
}

int main(){
    scanf("%d", &n);
    scanf("%d", &m);
    
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(i != j)
                city[i][j] = INF;
        }
    }
    
    for(int i=0; i<m; i++){
        int c1, c2, cost;
        
        scanf("%d %d %d", &c1, &c2, &cost);
        city[c1][c2] = MIN(city[c1][c2], cost);
        //city[c2][c1] = cost;
    }
    
    floyd();
    
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(city[i][j] == INF){
                city[i][j] = 0;
            }
            printf("%d ", city[i][j]);
        }
        printf("\n");
    }
}
