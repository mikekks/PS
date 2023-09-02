#include <iostream>
#include <map>

using namespace std;

int arr[41];
int N,S;
long long ans;
map<int, int> table;

void left(int cur, int sum){
    if(cur == N/2){
        table[sum]++;
        return;
    }
    
    left(cur+1, sum);
    left(cur+1, sum+arr[cur]);
}

void right(int cur, int sum){
    if(cur == N){
        ans += table[S-sum];
        return;
    }
    
    right(cur+1, sum);
    right(cur+1, sum+arr[cur]);
}

int main(){
    scanf("%d %d", &N, &S);
    
    for(int i=0; i<N; i++){
        scanf("%d", &arr[i]);
    }
    
    left(0,0);
    right(N/2, 0);
    
    if(S == 0){
        cout << ans-1 << '\n';
    }
    else{
        cout << ans << '\n';
    }
    
}
