#include <iostream>
#include <vector>
#include <algorithm>

#define MAX 100001
using namespace std;

int result;
int N;

int main(){
    cin >> N;
    
    vector<pair<int, int>> t;
    
    for(int i=0; i<N; i++){
        int t1,t2;
        cin >> t1 >> t2;
        t.push_back({t2,t1});
    }
    
    sort(t.begin(), t.end());
    
    int End = t[0].first;
    result++;
    
    for(int i=1; i<t.size(); i++){
        if(End <= t[i].second){
            End = t[i].first;
            result++;
        }
    }
    
    cout << result << '\n';
}
