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

int L, C;


vector<char> arr;
vector<string> ans;
bool isSelect[MAX];

int calculateCnt(int num){
    int cnt = 0;
    
    while(num != 0){
        if((num | 1) == num){
            cnt++;
        }
        num = num >> 1;
    }
    
    return cnt;
}

string getEncrypt(int num){
    string ret;
    int idx = 0;
    
    while(num != 0){
        if((num | 1) == num){
            ret += arr[idx];
        }
        num = num >> 1;
        idx++;
    }
    
    return ret;
}

void dfs(int idx, int select, int check1, int check2){
    
    if(calculateCnt(select) == L){
        if(check1 >= 1 && check2 >= 2 && !isSelect[select]){
            isSelect[select] = true;
            ans.push_back(getEncrypt(select));
        }
        return;
    }
    
    
    for(int i=idx+1; i<C; i++){
        int next = select;
        next = next | (1 << i);
        int next_check1 = check1;
        int next_check2 = check2;
        
        if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u'){ // 모음 처리
            next_check1++;
        }
        else {  // 자음 처리
            next_check2++;
        }
    
        dfs(i, next, next_check1, next_check2);
    }
    
}

int main(){
    cin >> L >> C;
    
    for(int i=0; i<C; i++){
        char tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    sort(arr.begin(), arr.end());
    
    
    dfs(-1, 0, 0, 0);
    
    for(int i=0; i<ans.size(); i++){
        cout << ans[i] << '\n';
    }
   
}
