let headerComponent = {
    template:
    `
        <div class="container mx-auto px-8 h-12 my-5 flex items-center justify-between w-1000">
            <div class="inline-block">
                로고/home
            </div>
            <div class="flex items-center justify-between">
                <div class="relative">
                    <div class="absolute inset-y-0 start-0 flex items-center ps-3.5 pointer-events-none">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                            <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                        </svg>
                    </div>
                    <input type="text" id="input-group-1" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block ps-10 p-2.5" placeholder="search channel">
                </div>
            </div>
            <div class="inline-block">로그인</div>
        </div>
    `,
    data() {
        return {
            name: 'QuiD!'
        }
    },
    methods: {},
}

