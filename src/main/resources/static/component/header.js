let headerComponent = {
    template:
    `
        <div class="px-5 py-2 flex items-center justify-between shadow-lg">
            <div class="flex">
                <a href="/" class="flex items-center">
                    <img src="/asset/logo.png" style="width: 150px">
                </a>
            </div>
            <div class="flex-1 items-center justify-between">
                <div class="max-w-md mx-auto">
                    <label for="default-search" class="mb-2 text-sm font-medium text-gray-900 sr-only">Search</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 start-0 flex items-center ps-4 pointer-events-none">
                            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                            </svg>
                        </div>
                        <input type="search" id="default-search" class="block w-full p-3 ps-10 pe-16 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:outline-none focus:ring-red-500 focus:border-red-500 " placeholder="채널을 찾아보세요" required />
                        <button type="submit" @click="search" class="text-white absolute end-1.5 bottom-1.5 bg-red-400 hover:bg-red-500 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm px-4 py-2">검색</button>
                    </div>
                </div>
            </div>
            <div v-if="isNotLogin" class="flex">
                <button type="button" class="py-2 px-5 m-1 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100" @click="toLoginPage">로그인</button>
                <span class="relative flex h-3 w-3 end-3">
                    <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-sky-400 opacity-75"></span>
                    <span class="relative inline-flex rounded-full h-3 w-3 bg-sky-500"></span>
                </span>
            </div>
            <div v-else class="flex">
                <button type="button" class="py-2 px-5 m-1 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100">로그아웃</button>
            </div>
        </div>
    `,
    data() {
        return {
            accessToken: '',
        }
    },
    methods: {
        search: function() {
            console.log('search');
        },
        toLoginPage: function() {
            location.href = '/login';
        },
    },
    computed: {
        isNotLogin: function() {
            return this.accessToken === '';
        },
        updateAccessToken: function() {
            return memberStore.state.accessToken;
        }
    },
    watch: {
        updateAccessToken: function(val) {
            this.accessToken = val;
        }
    },
    mounted: function() {
        this.accessToken = memberStore.state.accessToken;
    }
}

