import { defineConfig } from "vite";
import { createVuePlugin } from "vite-plugin-vue2-jsx";
// import {vueJsx} from '@vitejs/plugin-vue2-jsx'

// https://vitejs.dev/config/
const path = require("path");
export default defineConfig({
  plugins: [
    createVuePlugin({
      jsx: true, // JSX　サポート
      include: /src\/.*\.vue?$/,
    })
  ],

  server: {
    proxy: {
      // with options: http://localhost:8080/api/login-> http://localhost:1111/login
      "/api": {
        target: "http://localhost:1111",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  esbuild: {
    loader: "jsx",
    include: /src\/.*\.vue?$/,
    // include: /src\/.*\.[tj]sx?$/,
    exclude: [],
  },
//   optimizeDeps: {
//     esbuildOptions: {
//       loader: { ".js": "jsx" },
//       plugins: [
//         {
//           name: "load-js-files-as-jsx",
//           setup(build) {
//             build.onLoad({ filter: /src\/.*\.vue$/ }, async (args) => ({
//               loader: "jsx",
//               contents: await fs.readFile(args.path, "utf8"),
//             }));
//           },
//         },
//       ],
//     },
//   },
  extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
});
