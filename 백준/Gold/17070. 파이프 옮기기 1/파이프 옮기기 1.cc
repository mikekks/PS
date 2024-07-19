#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 20
#define INF 987654321

using namespace std;

int N;
int map[MAX][MAX];
int dp[MAX][MAX][3];

int arrow_y[3] = {0, 1, 1}; // →, ↘, ↓
int arrow_x[3] = {1, 1, 0};

// 0: 가로, 1: 대각선, 2: 세로

int dfs(int cy, int cx, int dir) {
    if (cy == N - 1 && cx == N - 1) {
        return 1;
    }

    if (dp[cy][cx][dir] != -1) {
        return dp[cy][cx][dir];
    }

    dp[cy][cx][dir] = 0;

    for (int i = 0; i < 3; i++) {
        int ny = cy + arrow_y[i];
        int nx = cx + arrow_x[i];

        if (ny >= N || nx >= N || map[ny][nx] == 1) continue;

        if (i == 1 && (map[cy][cx + 1] == 1 || map[cy + 1][cx] == 1)) continue;

        if ((dir == 0 && i == 2) || (dir == 2 && i == 0)) continue;

        dp[cy][cx][dir] += dfs(ny, nx, i);
    }

    return dp[cy][cx][dir];
}

int main() {
    cin >> N;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> map[i][j];
        }
    }

    memset(dp, -1, sizeof(dp));

    if (map[0][1] == 0) {
        cout << dfs(0, 1, 0) << '\n';
    } else {
        cout << 0 << '\n';
    }

    return 0;
}
