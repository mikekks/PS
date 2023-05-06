#include <iostream>
#include <deque>

using namespace std;

int main() {

    deque<int> deque_int;

    int n;
    cin >> n;
    
    string command;
    int command_num;
    
    for(int i=0;i<n;i++) {
        cin >> command;
        
        if (command == "push_front") {
            cin >> command_num;
            deque_int.push_front(command_num);
        } else if (command == "push_back") {
            cin >> command_num;
            deque_int.push_back(command_num);
        } else if (command == "size") {
            cout << deque_int.size() << endl;
        } else if (command == "empty") {
            cout << deque_int.empty() << endl;
        } else if (deque_int.empty()) {
            cout << -1 << endl;
        } else if (command == "pop_front") {
            cout << deque_int.front() << endl;
            deque_int.pop_front();
        } else if (command == "pop_back") {
            cout << deque_int.back() << endl;
            deque_int.pop_back();
        } else if (command == "front") {
            cout << deque_int.front() << endl;
        } else if (command == "back") {
            cout << deque_int.back() << endl;
        }
    }
    
    return 0;
}
