#include <iostream>
#include <cstring>
#include <stack>

using namespace ::std;

string nota;
stack<char> st;
string result;

int oper(char q){
    if(q == '+' || q == '-'){
        return 1;
    }else if(q == '*' || q == '/'){
        return 2;
    }else{
        return 0;
    }
}

void check(char q){
    if(st.empty()){
        st.push(q);
        return;
    }
    char tmp = oper(q);
  
    while(!st.empty()){
        if(oper(st.top()) >= tmp){
            char p = st.top();
            result += p;
            st.pop();
        }else{
            st.push(q);
            return;
        }
    }
    st.push(q);
}

int main(){
    cin >> nota;
    
    for(int i=0; i<nota.length(); i++){
        if(nota[i] == '+' || nota[i] == '-' || nota[i] == '*' || nota[i] == '/'){
            check(nota[i]);
        }else if(nota[i] == '('){
            st.push(nota[i]);
        }else if(nota[i] == ')'){
            
            while(st.top() != '('){
                char t = st.top();
                result += t;
                st.pop();
            }
            st.pop();
            
        }else{
            result += nota[i];
        }
    }
    
    while(!st.empty()){
        char t = st.top();
        result += t;
        st.pop();
    }
    
    cout << result << '\n';
}
