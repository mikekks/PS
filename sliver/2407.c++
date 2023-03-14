#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

#define MAX 101

using namespace::std;

int N,M;

string arr[MAX][MAX];

string addCon(string s1, string s2){
    string num = "";
    
    int sum = 0;
    int size = max(s1.size(),s2.size());

    for(int i=0; i<size || sum;i++){   // || sum은 마지막 지릿수 올림일 경우 생각
        if(s1.size()>i) sum += s1[i]-'0';
        if(s2.size()>i) sum += s2[i]-'0';

        num += sum%10 +'0';
        sum /= 10;
    }

        return num;
}

string conbi(int n, int m){
    
    if(n == m || m == 0){
        return "1";
    }
 
    if(arr[n][m] != ""){
        return arr[n][m];
    }
    
    arr[n][m] = addCon(conbi(n-1, m),conbi(n-1, m-1));
    
    return arr[n][m];
    
}

int main(){
    scanf("%d %d", &N, &M);
    
    conbi(N,M);
    
    for(int i=arr[N][M].size()-1; i>=0; i--)
            cout << arr[N][M][i];
    
    cout << '\n';
    
}
