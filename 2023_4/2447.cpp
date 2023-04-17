#include <iostream>
#include <cstring>
#include <algorithm>
#include <climits>
#include <cstring>

using namespace std;

char map[6562][6562];
int N;

void star(int x, int y, int num){
    
    if(num == 3){
        for(int i=x; i<x+3; i++){
            for(int j=y; j<y+3; j++){
                if(i == x+1 && j == y+1)
                    continue;
                map[i][j] = 1;
            }
        }
        
        return;
    }
    
    int tmp = num/3;
    for(int i=x; i<x+num; i+=tmp){
        for(int j=y; j<y+num; j+=tmp){
            if(i == x+tmp && j == y+tmp)
                continue;
            star(i,j,tmp);
        }
    }
    
}

int main(void){
    scanf("%d", &N);
    
    star(0, 0, N);
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(map[i][j] == 1)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }
    
    
}
