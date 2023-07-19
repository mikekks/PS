#include<iostream>
#include <cstring>

using namespace std;

struct Node {
    int data;
    Node* next;
    Node* prev;
};

const int MAX_NODE = 3001;
int N, M, L;
int input[1001];


int node_count = 0;
Node node_pool[MAX_NODE];

Node* new_node(int data) {
    node_pool[node_count].data = data;
    node_pool[node_count].next = nullptr;
    node_pool[node_count].prev = nullptr;
    return &node_pool[node_count++];
    
}

class DoublyLinkedList {
    Node* head;

public:
    DoublyLinkedList() = default;

    void init() {
        node_count = 0;
        head = new_node(-3);
        head->next = nullptr;
        head->prev = nullptr;
    }
    
    void pushBack(){
        Node* ptr = head;
        
        for(int i=0; i<N; i++){
            Node* node = new_node(input[i]);
            ptr->next = node;
            node->prev = ptr;
            ptr = ptr->next;
        }
    }
    
    void addNode2Head(int data)
    {
        Node* node = new_node(data);
        
        node->next = head->next;
        head->next = node;
        node->prev = head;
        
        if(node->next != nullptr)
            node->next->prev = node;
    }
    
    void addNode2Tail(int data)
    {
        Node* ptr = head;
        while(true){
            if(ptr->next == nullptr)
                break;
            ptr = ptr->next;
        }
        
        Node* node = new_node(data);
        ptr->next = node;
        node->prev = ptr;
    }

    void addNode2Num(int data, int num)
    {
        Node* ptr = head;
        for(int i=0; i<num; i++){
            ptr = ptr->next;
        }
        ptr = ptr->prev;
        
        if(num == 0){
            addNode2Head(data);
        }
        else{
            Node* node = new_node(data);
            node->next = ptr->next;
            ptr->next = node;
            node->prev = ptr;
            if(node->next != nullptr)
                node->next->prev = node;
        }
    }

    int findNode(int index)
    {
        Node* prev_ptr = head->next;
        int i = 0;
        while (prev_ptr != nullptr && i<index) {
            prev_ptr = prev_ptr->next;
            i++;
        }
        
        if (prev_ptr != nullptr) {
            return prev_ptr->data;
        }
        return -1;
    }
    
    void changeNode(int index, int data)
    {
        Node* prev_ptr = head;
        int i = -1;
        while (prev_ptr->next != nullptr && i<index) {
            prev_ptr = prev_ptr->next;
            i++;
        }
        
        if (prev_ptr != nullptr) {
            prev_ptr->data = data;
        }
    }

    void removeNode(int index)
    {
        Node* prev_ptr = head;
        int i = -1;
        while (prev_ptr->next != nullptr && i<index) {
            prev_ptr = prev_ptr->next;
            i++;
        }
        

        if (prev_ptr != nullptr) {
            prev_ptr->prev->next = prev_ptr->next;
            
            if(prev_ptr->next != nullptr)
                prev_ptr->next->prev = prev_ptr->prev;
        }
    }
};

int main(int argc, char** argv)
{
    int test_case;
    int T;
   
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        memset(node_pool, '\0', sizeof(node_pool));
        memset(input, '\0', sizeof(input));
        
        cin >> N >> M >> L;
        
        DoublyLinkedList dlist;
        dlist.init();

        
        for(int i=0; i<N; i++){
            cin >> input[i];
        }
        dlist.pushBack();
        
        for(int i=0; i<M; i++){
            char oper;
            cin >> oper;
            int a,b;
            
            if(oper == 'I'){
                cin >> a >> b;
                dlist.addNode2Num(b,a);
            }
            else if(oper == 'D'){
                cin >> a;
                dlist.removeNode(a);
            }
            else if(oper == 'C'){
                cin >> a >> b;
                dlist.changeNode(a,b);
            }
        }

        cout << "#" << test_case << " " << dlist.findNode(L) << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
