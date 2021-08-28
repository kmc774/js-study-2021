import Vue from "vue";
import VueRouter from "vue-router";
import BoardList from "@/views/board/boardList";
import BoardDetail from "@/views/board/BoardDetail";
import BoardWrite from "@/views/board/BoardWrite";

Vue.use(VueRouter); // Vue 안에서 VueRouter를 사용하겠다는 뜻

    const boardProps = (route) => {
        const props =  {
            boardPath: route.matched[0].path
        }
        props.query = route.query;
        return props;
    };


// 어떤 View를 뿌려줄지 바운딩 해주는 역할이라고 볼 수 있다.
const router = new VueRouter({
    mode: "history",
    routes: [ // Path를 어디로 넘길건지! --> <router-view>에 바인딩 된다.
        {
            path:"/" ,  // 기본 패스일 경우 list로 보내기
            redirect: '/list'

        },
        {
            path: "/list",
            name: "list",
            component: BoardList,
            props: boardProps
        },
        {
            path: "/view/:bdIdx" ,
            name: "view",
            component: BoardDetail,
            props: boardProps
        },
        {
            path: "/write" ,
            name: "write",
            component: BoardWrite,
            props: boardProps
        }

    ]
});


export default router; // 꼭 설정 !