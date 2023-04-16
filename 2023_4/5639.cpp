#include <iostream>

#define MAX 10000
using namespace::std;


int b[MAX];

void postfix(int front, int back){
    if(front >= back){
        return;
    }
    
    if (front == back - 1) {
        cout << b[front] << '\n';
        return;
    }
    int i = front + 1;
    while (i<back) {
        if (b[front]<b[i]) {
            break;
        }
        i++;
    }
        
    postfix(front+1, i);
    postfix(i, back);
    cout << b[front] << '\n';
    
}



int main(){
    
    int tmp;
    int i=0;
    while(cin >> tmp){
        b[i++] = tmp;
    }
    
    postfix(0, i);
}
