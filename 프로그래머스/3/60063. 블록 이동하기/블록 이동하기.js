const getKey = (x, y, x1, y1) => {
    const sort = [[x,y],[x1,y1]].sort((a,b) => {
        if(a[0] !== b[0]) {
            return a[0] - b[0];
        }
        return a[1] - b[1];
    });

    return `${sort[0][0]},${sort[0][1]},${sort[1][0]},${sort[1][1]}`;
}

const dx = [1,0,-1, 0];
const dy = [0,1,0,-1];
const check = (p1, p2, board) => {
    const result = [];
    const [x1, y1] = p1;
    const [x2, y2] = p2;

    for(let i = 0; i <dx.length; i++) {
        const nx1 = x1 + dx[i];
        const ny1 = y1 + dy[i];
        const nx2 = x2 + dx[i];
        const ny2 = y2 + dy[i];
        if(nx1 >= 0 && nx1 < board.length && nx2 >= 0 && nx2 < board.length){
            if(ny1 >= 0 && ny1 < board.length && ny2 >= 0 && ny2 < board.length) {
                if(board[nx1][ny1] === 0 && board[nx2][ny2] === 0){
                    result.push([[nx1,ny1],[nx2,ny2]]);

                    if(x1 === x2 && dx[i] !== 0) { // 가로
                        result.push([[x1,y1],[nx1,ny1]]);
                        result.push([[x2,y2],[nx2,ny2]]);
                    } else if(y1 === y2 && dy[i] !== 0) { // 세로
                        result.push([[x1,y1],[nx1,ny1]]);
                        result.push([[x2,y2],[nx2,ny2]]);
                    }
                }   
            }
        }
    }

    return result;
}
const isLastNode = (p1, p2, board) => {
    const x = board.length -1;
    const y = board[0].length -1;

    if(p1[0] === x && p1[1] === y) {
        return true;
    }
    if(p2[0] === x && p2[1] === y) {
        return true;
    }
    return false;
}
// visited의 판별 기준 map
function solution(board) {
    var answer = Number.POSITIVE_INFINITY;

    const vmap = new Map();
    vmap.set(getKey(0,0,0,1),0);

    const dot1 = [0,0];
    const dot2 = [0,1];
    // bfs
    const q = [[dot1, dot2]];

    while(q.length) {
        const [p1, p2] = q.shift();
        const prevKey = getKey(p1[0], p1[1],p2[0], p2[1]);
        const prevCnt = vmap.get(prevKey);


        if(isLastNode(p1,p2, board)) {
            return prevCnt;
        }

        // 8가지에 대해서 가능한 것 확인 후 삽입.
        const result = check(p1,p2,board);
        result.forEach((point) => {
            const [point1 , point2] = point;
            const key = getKey(point1[0], point1[1],point2[0], point2[1]);
            const cnt = vmap.get(key);
            if(cnt === undefined || prevCnt + 1 < cnt) {
                vmap.set(key, prevCnt + 1);
                q.push([point1, point2]);
            }
        });
    }


}