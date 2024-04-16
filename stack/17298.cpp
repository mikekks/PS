#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <stack>

#define MAX 1000005

using namespace std;

int N, M;

int p[MAX];
int stack_max = 0;
int ans[MAX];
stack<int> st;


int main(){
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        p[i] = tmp;
        
        if(!st.empty()){
            while(tmp > p[st.top()]){
                int cur = st.top();
                st.pop();
                ans[cur] = tmp;
                if(st.empty()) break;
            }
        }
        st.push(i);
    }
    
    while(!st.empty()){
        int cur = st.top();
        st.pop();
        ans[cur] = -1;
    }
    
    for(int i=0; i<N; i++){
        cout << ans[i] << " ";
    }
}
