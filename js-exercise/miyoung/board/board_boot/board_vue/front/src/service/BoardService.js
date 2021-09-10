import Axios from "axios";
export class BoardService {

    constructor(port) {
        this.port = port;
    }

    getBoardList(searchParam) {
        return Axios
            .get(`${this.port}/board/list`, {params: searchParam})
            .then(response => {
                return response.data;
            })
            .catch((e) => {
                console.log(e);
            });

    }
    /*
    getBoardListLoading(searchParam) {
        return Axios
            .get(`${this.port}/board/list`, {params: searchParam})
            .then( response => {
                setTimeout(() => {
                    if(response.data.length) {
                        this.boardList = response.data.boardList
                        $state.loaded();
                        this.limit += 10
                        if(this.boardList.length / 10 === 0) {
                            $state.complete();
                        }
                    } else {
                        $state.complete();
                    }
                }, 1000)
            }).catch(error => {
            console.error(error);
        })

    } */

    getBoardDetail(bdIdx) {
        return Axios
            .get(`${this.port}/board/view/${bdIdx}`)
            .then(response => {
                return response.data;
            })
            .catch((e) => {
                console.log(e);
            });
    }

    insertBoard(formData) {
        return Axios
            .post(`${this.port}/board/write`, formData)
            .then((response) => {
                let result = response.data;
                if (result == 'success') {
                    alert('게시글이 등록되었습니다.');
                    location.href = '/';
                } else if (result == 'extension') {
                    alert('등록할 수 없는 형태의 파일이 존재합니다.');
                } else if (result == 'maxSize') {
                    alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
                } else if (result == 'SumMaxSize') {
                    alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
                } else if (result == 'title') {
                    alert('제목을 입력해주세요.');
                } else if (result == 'content') {
                    alert('내용을 입력해주세요.');
                } else if (result == 'userId') {
                    alert('작성자를 입력해주세요.');
                } else if (result == 'fail') {
                    alert('게시글 등록에 실패했습니다.');
                    location.href = '/';
                }
            })
            .catch((e) => {
                console.log(e);
            });


    }

    updateBoard(bdIdx, formData) {
        return Axios
            .put(`${this.port}/board/update/${bdIdx}`, formData)
            .then((response) => {
                let result = response.data;
                if (result == 'success') {
                    alert('게시글이 수정되었습니다.');
                    return 'success';
                } else if (result == 'extension') {
                    alert('등록할 수 없는 형태의 파일이 존재합니다.');
                } else if (result == 'maxSize') {
                    alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
                } else if (result == 'SumMaxSize') {
                    alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
                } else if (result == 'title') {
                    alert('제목을 입력해주세요.');
                } else if (result == 'content') {
                    alert('내용을 입력해주세요.');
                } else if (result == 'userId') {
                    alert('작성자를 입력해주세요.');
                } else if (result == 'fail') {
                    alert('게시글 등록에 실패했습니다.');
                }
            })
    }

    deleteBoard( bdIdx ){
        return Axios
            .delete(`${this.port}/board/delete/${bdIdx}`)
            .then((response) => {
                return response.data;
            })
    }

    downloadFile( fileIdx ){
        return Axios
            .get(`${this.port}/board/download/${fileIdx}`,{
                headers : { "Access-Control-Allow-Origin" : '*' } ,
                responseType: 'blob' // blob를 넣지 않으면 txt 파일은 읽히지만 png 파일이 읽히지 않는다. ( 지원되지 않는 형식이라고 뜸 )
            })
            .then((response) => {
                const name = response.headers['content-disposition'].split("filename*=UTF-8''")[1];
                const decodeName = decodeURIComponent(name);  // 파일명이 깨지기 때문에 다시 decode 진행
                // window.URL.createObjectURL : 특정 파일 객체나 로컬 파일 또는 데이터의 참조를 가리키는 새로운 객체 URl을 생성해준다.
                // Blob : Binary Large Object : 멀티미디어 객체 저장을 위해 사용
                const url = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', decodeName );
                document.body.appendChild(link);
                link.click();
            })
    }

    addComment( bdIdx , bdComment ){
        return Axios
            .get(`${this.port}/board/write/comment/${bdIdx}/${bdComment}`)
            .then((response) => {
                return response.data;
            })
    }

    updateComment( bdIdx, comIdx , bdComment) {
        return Axios
            .get(`${this.port}/board/update/comment/${bdIdx}/${comIdx}/${bdComment}`)
            .then((response )=> {
                return response.data;
            })
    }

    deleteComment( comIdx ){
        return Axios.delete(`${this.port}/board/delete/comment/${comIdx}`)
    }

    countLike( bdIdx ){
        return Axios.get(`${this.port}/board/count/like/${bdIdx}`)
            .then((response) => {
                return response.data;
            })
    }

    countView( bdIdx ){
        return Axios.get(`${this.port}/board/count/view/${bdIdx}`)
            .then((response => {
                return response.data;
            }))
    }


}