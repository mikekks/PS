import java.util.*;

public class Solution {

	Node preNode = new Node(' ');
	Node postNode = new Node(' ');

	public class Node {
		char c;
		Map<Integer, Integer> count;
		Map<Character, Node> child;

		public Node(char c) {
			this.c = c;
			this.child = new HashMap<>();
			this.count = new HashMap<>();
		}
	}

	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		for (String word : words) {
			add(preNode, word, 0);

			StringBuilder sb = new StringBuilder(word);
			StringBuilder reverse = sb.reverse();
			add(postNode, reverse.toString(), 0);
		}

		for (int i = 0; i < queries.length; i++) {

			if (queries[i].charAt(0) != '?') { // pre
				answer[i] = query(preNode, queries[i], 0);
			} else {
				StringBuilder sb = new StringBuilder(queries[i]);
				StringBuilder reverse = sb.reverse();
				answer[i] = query(postNode, reverse.toString(), 0);
			}
		}

		return answer;
	}

	public void add(Node node, String str, int idx) {

		if (idx == str.length())
			return;

		char c = str.charAt(idx);

		Node newNode;
		if (!node.child.containsKey(c)) {
			newNode = new Node(c);
			node.child.put(c, newNode);
		} else {
			newNode = node.child.get(c);
		}

		Integer cnt = newNode.count.getOrDefault(str.length() - idx - 1, 0);
		newNode.count.put(str.length() - idx - 1, cnt + 1);

		add(newNode, str, idx + 1);
	}

	public int query(Node node, String query, int idx) {

		char q = query.charAt(idx);
		int ret = 0;

		if (node.child.containsKey(q)) {
			Node child = node.child.get(q);
			return query(child, query, idx + 1);
		} else if (q == '?') {
			for (Character c : node.child.keySet()) {
				Node child = node.child.get(c);
				ret += child.count.getOrDefault(query.length() - idx - 1, 0);
			}
		}

		return ret;
	}
}