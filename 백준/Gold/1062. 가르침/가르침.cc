#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 2005

using namespace std;
int N, K;
int ans = 0;

vector<string> arr;
bool selected[27];

void dfs(int idx, int cnt){
    if(cnt == K){
        int tmp_ans = 0;
        for(int i=0; i<N; i++){
            
            bool hit = true;
            for(int j=0; j<arr[i].length(); j++){
                if(selected[arr[i][j] - 'a'] == false){
                    hit = false;
                    break;
                }
            }
            
            if(hit) tmp_ans++;
        }
        
        ans = tmp_ans > ans ? tmp_ans : ans;
        
        return;
    }
    
    for(int i=idx; i<26; i++){
        if(!selected[i]){
            selected[i] = true;
            dfs(i, cnt+1);
            selected[i] = false;
        }
    }
    
}


int main() {
    cin >> N >> K;
    
    for(int i=0; i<N; i++){
        string tmp;
        cin >> tmp;
        
        arr.push_back(tmp);
    }
    

    
    if(K < 5){     // a, n, t, i, c
        cout << 0 << '\n';
        return 0;
    }
    
    selected['a'-'a'] = true;
    selected['n'-'a'] = true;
    selected['t'-'a'] = true;
    selected['i'-'a'] = true;
    selected['c'-'a'] = true;
    
    // dfs
    dfs(0, 5);
    
    cout << ans << '\n';
}
