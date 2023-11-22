#include <string>
#include <vector>
#include <stack>


using namespace std;

struct node {
    node *next;
    node *prev;
    int value;
    bool live;
};

node node_pool[1000000];
node* cur;
stack<int> st;


void clear() {
    
    node *next_now = (cur->next != nullptr)? cur->next : cur->prev;
    
    if (cur->prev != nullptr) {
        cur->prev->next = cur->next;
    }
    if (cur->next != nullptr) {
        cur->next->prev = cur->prev;
    }
    
    cur->live = false;
    cur->prev = nullptr;
    cur->next = nullptr;
    st.push(cur->value);

    cur = next_now;
}

void recover(int n) {
    int recover_row = st.top();
    st.pop();
    node* rNode = &node_pool[recover_row];
    
    int next_row = recover_row+1;
    while (next_row < n) {
        if (node_pool[next_row].live) {
            break;
        }
        next_row++;
    }
    
    if (next_row != n) {
        rNode->next = &node_pool[next_row];
        rNode->prev = node_pool[next_row].prev;
        if (rNode->prev != nullptr) {
            rNode->prev->next = rNode;
        }
        node_pool[next_row].prev = rNode;
    } else {
        int prev_row = recover_row-1;
        while (prev_row >= 0) {
            if (node_pool[prev_row].live) {
                break;
            }
            prev_row--;
        }
        if (prev_row != -1) {
            rNode->prev = &node_pool[prev_row];
            node_pool[prev_row].next = rNode;
        }
    }
    rNode->live = true;
}

void init(int n){
    for (int i = 0; i < n; i++) {
        node_pool[i].value = i;
        node_pool[i].live = true;
        node_pool[i].prev = (i != 0)? &node_pool[i-1] : nullptr;
        node_pool[i].next = (i != n-1)? &node_pool[i+1] : nullptr;
    }
}


string solution(int n, int k, vector<string> cmd) {
    init(n);
    
    cur = &node_pool[k];
    
    for (auto command: cmd) {
        char type = command[0];
        if (type == 'U') {
            int cnt = stoi(command.substr(2));
            for (int i = 0; i < cnt; i++) {
                cur = cur->prev;
            }
        } else if (type == 'D') {
            int cnt = stoi(command.substr(2));
            for (int i = 0; i < cnt; i++) {
                cur = cur->next;
            }
        } else if (type == 'C') {
            clear();
        } else {
            recover(n);
        }
    }
    string answer = "";
    for (int i = 0; i < n; i++) {
        answer.push_back((node_pool[i].live)? 'O' : 'X');
    }
    return answer;
}
