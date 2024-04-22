#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>

using namespace std;

int N;
int ans;
int mid;

priority_queue<int, vector<int>, greater<>> large_q;
priority_queue<int> small_q;



int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        
        if(i == 0){
            mid = tmp;
            cout << mid << '\n';
            continue;
        }
        
        
        if(tmp > mid){
            large_q.push(tmp);
        }
        else{
            small_q.push(tmp);
        }
        
        
        int diff = large_q.size() - small_q.size();
        if(abs(diff) == 2){
            if(large_q.size() > small_q.size()){
                int cur = mid;
                small_q.push(cur);
                mid = large_q.top();
                large_q.pop();
            }
            else{
                int cur = mid;
                large_q.push(cur);
                mid = small_q.top();
                small_q.pop();
            }
        }
        else if(abs(diff) == 1){
            if(large_q.size() < small_q.size()){
                int cur = mid;
                large_q.push(cur);
                mid = small_q.top();
                small_q.pop();
            }
        }

        cout << mid << '\n';
    }

   
}
