#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 10005


using namespace std;

int A, B;
int isVisit[MAX];
int total = 987654321;
string ans;

int L(int n) {
    int d1 = n / 1000;
    int d2 = (n % 1000) / 100;
    int d3 = (n % 100) / 10;
    int d4 = n % 10;
    return (d2 * 1000) + (d3 * 100) + (d4 * 10) + d1;
}

int R(int n) {
    int d1 = n / 1000;
    int d2 = (n % 1000) / 100;
    int d3 = (n % 100) / 10;
    int d4 = n % 10;
    return (d4 * 1000) + (d1 * 100) + (d2 * 10) + d3;
}

void bfs(){
    total = 987654321;
    queue<pair<int, string>> q;
    q.push({A, ""});
    isVisit[A] = true;
    
    while (!q.empty()) {
        int cur = q.front().first;
        string oper = q.front().second;
        q.pop();
        
        if(cur == B){
            if(oper.length() < total){
                total = oper.length();
                ans = oper;
            }
            break;
        }
        
        // D
        int d = (cur * 2) % 10000;
        if(!isVisit[d]){
            isVisit[d] = true;
            q.push({d, oper+"D"});
        }
        
        // S
        int s = cur == 0 ? 9999 : cur - 1;
        if(!isVisit[s]){
            isVisit[s] = true;
            q.push({s, oper+"S"});
        }
        
        // L
        int l = L(cur);
        if(!isVisit[l]){
            isVisit[l] = true;
            q.push({l, oper+"L"});
        }
        
        // R
        int r = R(cur);
        if(!isVisit[r]){
            isVisit[r] = true;
            q.push({r, oper+"R"});
        }
    }
}

int main() {
    int T;
    cin >> T;
    
    while(T--){
        cin >> A >> B;
        memset(isVisit, false, sizeof(isVisit));
        bfs();
        cout << ans << '\n';
        ans = "";
    }
    
}
