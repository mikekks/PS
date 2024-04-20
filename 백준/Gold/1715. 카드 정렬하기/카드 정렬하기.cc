#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <stack>

#define MAX 35000

using namespace std;

int N;
int ans;

vector<int> deck;
priority_queue<int, vector<int>, greater<>> q;


int main(){
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        q.push(tmp);
    }
    
    if(N == 1){
        cout << 0 << '\n';
        return 0;
    }
    
    while(q.size() > 1){
        int t1 = q.top();
        q.pop();
        int t2 = q.top();
        q.pop();
        int total = t1+t2;
        ans += total;
        
        if(q.empty()){
            break;
        }
        
        q.push(total);
    }
    
    cout << ans << '\n';

   
}
