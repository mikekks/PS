#include <string>
#include <vector>
#include <stack>
#include <cmath>

using namespace std;

bool ans;


string getValue(long long num){
    stack<int> st;
    int cnt = 1;
    int len = 0;
    string ret_string = "";
    
    while(num != 1){
        st.push(num%2);
        num /= 2;
        len++;
    }
    st.push(1);
    len++;

    while(len > pow(2, cnt) - 1){
        cnt++;
    }
    
    while(len < pow(2, cnt) - 1){
        st.push(0);
        len++;
    }
    
    while(!st.empty()){
        if(st.top() == 1) ret_string += "1";
        else ret_string += "0";
        st.pop();
    }
    
    return ret_string;
}

char check(int left, int right, string num){
    if(left == right){
        return num[left];
    }
    
    int mid = (left+right)/2;
    char L_ret = check(left, mid-1, num);
    char R_ret = check(mid+1, right, num);
    
    if(L_ret == '1' && num[mid] == '0') ans = false;
    if(R_ret == '1' && num[mid] == '0') ans = false;

    if(L_ret == '0' && R_ret == '0' && num[mid] == '0') return '0';
    return '1';
}

vector<int> solution(vector<long long> numbers) {
    vector<int> answer;
    
    for(int i=0; i<numbers.size(); i++){
        string ret = getValue(numbers[i]);
        ans = true;
        check(0, (int)ret.length()-1, ret);
        
        if(ans) answer.push_back(1);
        else answer.push_back(0);
        
    }
    
    
    return answer;
}
