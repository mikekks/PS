#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <vector>

using namespace std;

#define MAX_INPUT 10000
#define MAX_NUM 30000

extern void init();
extern void addUser(int uID, int income);
extern int getTop10(int result[10]);

static int input[MAX_INPUT];

static unsigned int seed = 13410;

static unsigned int pseudoRand() {
    seed = seed * 214013 + 2531011;
    return (unsigned int)(seed >> 11) % MAX_NUM;
}

static void makeInput(int inputLen) {
    for (int i = 0; i < inputLen; i++) {
        input[i] = pseudoRand();
    }
}

vector<pair<int, int>> v;

bool cmp(pair<int, int> p1, pair<int, int> p2){
    if(p1.first > p2.second){
        return true;
    }
    else if(p1.first == p2.first){
        if(p1.second < p2.second)
            return true;
    }else{
        return false;
    }
    return false;
}

static int run() {
    int N, userNum, uID = 0;
    int score = 100, result[10], cnt;
    int sum, check;
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        scanf("%d", &userNum);
        makeInput(userNum);
        for (int j = 0; j < userNum; j++) {
            addUser(uID++, input[j]);
            v.push_back({input[j], uID-1});
        }
        
        sort(v.begin(), v.end(), greater<pair<int, int>>());
        cnt = getTop10(result);
        
        int tmp = 0;
        

        sum = 0;
        for (int j = 0; j < cnt; j++){
            sum += result[j];
            tmp += v[j].second;
        }
        scanf("%d", &check);
        if (check != sum)
            score = 0;
    }
    return score;
}

int main(void) {
    setbuf(stdout, NULL);
    //freopen("partial_sort_input.txt", "r", stdin);
    int T;
    scanf("%d", &T);
    for (int tc = 1; tc <= T; tc++) {
        init();
        v.clear();
        printf("#%d %d\n", tc, run());
    }
    return 0;
}


#define parent (i >> 1)
#define left (i << 1)
#define right (i << 1 | 1)

int h_data[100005][2];
int h_size;

void init()
{
    h_size = 0;
    for(int i=0; i<100005; i++){
        h_data[i][0] = 0;
        h_data[i][1] = 0;
    }
}

void addUser(int uID, int income)
{
    ++h_size;
    h_data[h_size][0] = income;
    h_data[h_size][1] = uID;
    
    for (int i = h_size; parent != 0 && h_data[parent][0] <= h_data[i][0]; i >>= 1) {
        int tmp_v = h_data[parent][0];
        int tmp_id = h_data[parent][1];
        
        if(uID == 644 && tmp_v == 11096){
            
        }
        if(uID == 11096 && tmp_v == 644){
            
        }
        
        if(h_data[parent][0] == h_data[i][0]){
            if(h_data[parent][1] > h_data[i][1]){
                h_data[parent][0] = h_data[i][0];
                h_data[parent][1] = h_data[i][1];
                h_data[i][0] = tmp_v;
                h_data[i][1] = tmp_id;
            }
        }
        else{
            h_data[parent][0] = h_data[i][0];
            h_data[parent][1] = h_data[i][1];
            h_data[i][0] = tmp_v;
            h_data[i][1] = tmp_id;
        }

    }
}

void pop() {
    
    h_data[1][0] = h_data[h_size][0];
    h_data[1][1] = h_data[h_size][1];
    h_size--;
    
    for (int i = 1; left <= h_size;) {
        if (left == h_size || h_data[left][0] > h_data[right][0] ) {
            
            if (h_data[i][0] <= h_data[left][0]) {
                int tmp_h = h_data[left][0];
                int tmp_i = h_data[left][1];

                if(h_data[i][0] == h_data[left][0]){
                    if(h_data[i][1] > h_data[left][1]){
                        h_data[left][0] = h_data[i][0];
                        h_data[left][1] = h_data[i][1];
                        h_data[i][0] = tmp_h;
                        h_data[i][1] = tmp_i;
                        i = left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    h_data[left][0] = h_data[i][0];
                    h_data[left][1] = h_data[i][1];
                    h_data[i][0] = tmp_h;
                    h_data[i][1] = tmp_i;
                    
                    i = left;
                }
                
                
            } else {
                break;
            }
        }else if(h_data[left][0] == h_data[right][0] && h_data[left][1] < h_data[right][1]) {
            if (h_data[i][0] <= h_data[left][0]) {
                int tmp_h = h_data[left][0];
                int tmp_i = h_data[left][1];

                if(h_data[i][0] == h_data[left][0]){
                    if(h_data[i][1] > h_data[left][1]){
                        h_data[left][0] = h_data[i][0];
                        h_data[left][1] = h_data[i][1];
                        h_data[i][0] = tmp_h;
                        h_data[i][1] = tmp_i;
                        i = left;
                    }
                    else{
                        break;
                    }
                }
                else{
                    h_data[left][0] = h_data[i][0];
                    h_data[left][1] = h_data[i][1];
                    h_data[i][0] = tmp_h;
                    h_data[i][1] = tmp_i;
                    
                    i = left;
                }
                
                
            } else {
                break;
            }
        }
        else {
            if (h_data[i][0] <= h_data[right][0]) {
                int tmp_h = h_data[right][0];
                int tmp_i = h_data[right][1];

                if(h_data[i][0] == h_data[right][0]){
                    if(h_data[i][1] > h_data[right][1]){
                        h_data[right][0] = h_data[i][0];
                        h_data[right][1] = h_data[i][1];
                        h_data[i][0] = tmp_h;
                        h_data[i][1] = tmp_i;
                        
                        i = right;
                    }
                    else{
                        break;
                    }
                }
                else{
                    h_data[right][0] = h_data[i][0];
                    h_data[right][1] = h_data[i][1];
                    h_data[i][0] = tmp_h;
                    h_data[i][1] = tmp_i;
                    
                    i = right;
                }
            } else {
                break;
            }
        }
    }
}

int getTop10(int result[10])
{
    int cnt = 0;
    int value[10];
    
    
    for(int i=0; i<10; i++){
        if(h_size == 0){
            break;
        }
        
        result[i] = h_data[1][1];
        value[i] = h_data[1][0];
        cnt++;
        pop();
    }
    
    for(int i=0; i<cnt; i++){
        addUser(result[i], value[i]);
    }
    
    return cnt;
}
