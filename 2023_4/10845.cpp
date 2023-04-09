#include <iostream>
#include <queue>
#include <algorithm>

#define MAX 100001
using namespace::std;

int N;
queue<int> q;


int main(){
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        string s;
        cin >> s;
        
        if(s == "push"){
            int tmp;
            cin >> tmp;
            q.push(tmp);
        }else if(s == "pop"){
            if(q.empty()){
                cout << -1 << '\n';
            }else{
                int tmp = q.front();
                q.pop();
                cout << tmp << '\n';
            }
        }else if(s == "size"){
            cout << q.size() << '\n';
        }else if(s == "front"){
            if(q.empty()){
                cout << -1 << '\n';
            }else{
                int tmp = q.front();
                cout << tmp << '\n';
            }
        }else if(s == "back"){
            if(q.empty()){
                cout << -1 << '\n';
            }else{
                int tmp = q.back();
                cout << tmp << '\n';
            }
        }else{
            if(q.empty()){
                cout << 1 << '\n';
            }else{
                cout << 0 << '\n';
            }
        }
    }
    
}
