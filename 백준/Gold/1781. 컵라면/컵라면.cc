#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 200005

int N;
long long ans;


bool isCheck[MAX];
long long prev_arr[MAX];


long long start_time = 0;

struct Compare {
    bool operator()(const pair<long long, long long>& a, const pair<long long, long long>& b) {
        if (a.first == b.first) {
            return a.second > b.second;
        }
        return a.first < b.first;
    }
};



priority_queue<pair<long long, long long>, vector<pair<long long, long long>>, Compare> q;


void process(long long deadline, long long cup){
    
    long long time = prev_arr[deadline];
    
    while (isCheck[time] && time > start_time) {
           time--;
    }
    
    if(time == start_time){
       // start_time = time;
        prev_arr[deadline] = 0;
        return;
    }
    
    isCheck[time] = true;
    ans += cup;
    prev_arr[deadline] = time-1;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N;
    
    for(int i=1; i<=N; i++){
        long long d, c;
        cin >> d >> c;
        q.push({c,d});
        prev_arr[i] = i;
    }
    
    while (!q.empty()) {
        long long cup = q.top().first;
        long long deadline = q.top().second;
        q.pop();
        
        process(deadline, cup);
        
    }
    
    cout << ans << '\n';
    
    return 0;
}

/**
 5
 5 1
 5 2
 5 3
 5 4
 5 5
 
 **/
