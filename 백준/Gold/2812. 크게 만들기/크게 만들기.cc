#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 105

using namespace std;

int N, K;
string input;
stack<int> st;
stack<int> st_ans;
vector<int> ans;

int main(void)
{
    cin >> N >> K;
    cin >> input;
    
    st.push(input[0] - '0');
    
    for(int i=1; i<input.size(); i++){
        int cur = input[i] - '0';
        
        while(!st.empty() && K != 0 && cur > st.top()){
            st.pop();
            K--;
        }
        st.push(cur);
    }
    
    
    while(K != 0){
        st.pop();
        K--;
    }
    
    while(!st.empty()){
        st_ans.push(st.top());
        st.pop();
    }
    
    while(!st_ans.empty()){
        cout << st_ans.top();
        st_ans.pop();
    }
    cout << '\n';
    
    

    return 0;
}
