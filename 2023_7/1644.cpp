#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 4000001

using namespace::std;

int N;
int prime[MAX];
int dp[MAX];
int check[MAX];
int total;
vector<int> p_sum;

int main(){
    
    scanf("%d", &N);
    
    for(int i=2; i<=N; i++){
        if(prime[i] == 0){
            for(int j=2; j*i<=N; j++){
                prime[i*j] = 1;
            }
        }
    }
    
    p_sum.push_back(0);
    int sum = 0;
    for(int i = 2; i < MAX; i++)
        {
            if(prime[i] == 0)
            {
                sum += i;
                p_sum.push_back(sum);
            }
        }
    
    int l = 0, r = 0;
    
    while(l<=r && r<p_sum.size()){
        if(p_sum[r] - p_sum[l] > N){
            l++;
        }
        else if(p_sum[r] - p_sum[l] < N){
            r++;
        }
        else{
            total++;
            r++;
        }
    }
    
    printf("%d\n", total);
    
    return 0;
}
