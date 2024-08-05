#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 505

using namespace std;

int H, W;
int block[MAX];
int height[MAX];
int ans;
int map[MAX][MAX];


int main(void)
{
    cin >> H >> W;
  
    for(int i=1; i<=W; i++){
        cin >> block[i];
        for(int j=1; j<=block[i]; j++){
            map[j][i] = 1;
        }
    }
    
    for(int h=1; h<=H; h++){
        stack<int> st;
        
        for(int w=1; w<=W; w++){
            if(map[h][w] == 1){
                if(st.empty()){
                    st.push(w);
                }
                else{
                    int prev = st.top();
                    st.pop();
                    st.push(w);
                    
                    int water = w-prev-1;
                    if(water > 0){
                        ans += water;
                    }
                }
            }
        }
    }
    
    
//    for(int i=0; i<W; i++){
//        if(st.empty()){
//            st.push({i, block[i]});
//            continue;
//        }
//
//        int h = 987654321;
//        int w = 987654321;
//
//        while (!st.empty() && block[i] >= st.top().second) {
//            if(block[i] == st.top().second){
//                w = st.top().first + 1;
//                break;
//            }
//            else{
//                w = st.top().first < w ? st.top().first : w;
//                h = st.top().second < h ? st.top().second : h;
//                st.pop();
//            }
//        }
//
//        if(h != 987654321 && w != 987654321 && !st.empty()){
//            ans += (block[i]-h) * (i-w);
//        }
//
//        st.push({i, block[i]});
//
//    }
    
    cout << ans << '\n';
    

    return 0;
}
