#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

bool bfs(vector<vector<pair<int, int>>> &graph, int start, int end, int weight_limit, int n) {
    vector<bool> visited(n + 1, false);
    queue<int> q;
    q.push(start);
    visited[start] = true;

    while (!q.empty()) {
        int node = q.front();
        q.pop();

        for (auto &neighbor : graph[node]) {
            int next_node = neighbor.first;
            int weight = neighbor.second;

            if (!visited[next_node] && weight >= weight_limit) {
                visited[next_node] = true;
                q.push(next_node);
                if (next_node == end) {
                    return true;
                }
            }
        }
    }

    return false;
}

int max_weight_limit(int n, int m, vector<tuple<int, int, int>> &bridges, int factory1, int factory2) {
    vector<vector<pair<int, int>>> graph(n + 1);

    int min_weight = 1, max_weight = 0;
    for (auto &[a, b, c] : bridges) {
        graph[a].emplace_back(b, c);
        graph[b].emplace_back(a, c);
        max_weight = max(max_weight, c);
    }

    int result = min_weight;

    while (min_weight <= max_weight) {
        int mid = (min_weight + max_weight) / 2;

        if (bfs(graph, factory1, factory2, mid, n)) {
            result = mid;
            min_weight = mid + 1;
        } else {
            max_weight = mid - 1;
        }
    }

    return result;
}

int main() {
    int n, m;
    cin >> n >> m;
    vector<tuple<int, int, int>> bridges(m);

    for (int i = 0; i < m; ++i) {
        int a, b, c;
        cin >> a >> b >> c;
        bridges[i] = make_tuple(a, b, c);
    }

    int factory1, factory2;
    cin >> factory1 >> factory2;

    cout << max_weight_limit(n, m, bridges, factory1, factory2) << endl;

    return 0;
}

