#include <iostream>
#include <stack>
#include <cstring>
#include <list>

using namespace::std;

string s;
string boom;
string result = "";
bool Empty = true;

int main(){
    cin >> s;
    cin >> boom;
    
    int e = boom.length() - 1;
 
    
    for(int i=0; i<s.length(); i++){
        result += s[i];
        int idx = result.length()-1;
        if(result[idx] == boom[e]){
            if(result.length()>=boom.length()){
                int cnt = 1;
                for(int j=0; j<e; j++){
                    if(result[idx-e+j] == boom[j]){
                        cnt++;
                    }
                }
                if(cnt == e+1){  // boom
                    for(int b = 0; b < boom.length(); b++){
                        result.pop_back();
                    }

                }
            }
        }
    }
    
    if(result.length()>0){
        cout << result << '\n';
    }else{
        cout << "FRULA" << '\n';
    }
    
    
}
