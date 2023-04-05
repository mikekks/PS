#include<iostream>
#include<queue>

using namespace std;

int N;

priority_queue<int, vector<int>, greater<int>> q;

int main() {
    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        int tmp;
        scanf("%d", &tmp);

        if (tmp == 0) {
            if (q.empty()) {
                printf("0\n");
            }
            else {
                printf("%d\n", q.top());
                q.pop();
            }
        }
        else {
            q.push(tmp);
        }
    }

    return 0;

}
