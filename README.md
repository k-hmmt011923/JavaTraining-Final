# JavaTraining-Week4  
- Spring Boot を用いたタスク管理API


## 概要

- Spring Boot および Spring Data JPA を用いて、  タスクの作成・取得・更新・削除（CRUD）を行うRESTAPIを実装しました。


- 入力値のバリデーションや、存在しないリソースへのアクセス時には  適切な HTTPステータスコード（400 / 404）を返すようにしています。


### 環境構築手順
- Java 21
- Gradle
- H2 Database（ファイルベース）


### アプリケーションの起動 Gradle を使用

- ./gradlew bootRun

- gradlew.bat bootRun (上記で実行できない場合)


## 実行確認手順

### タスク一覧取得(GET)
- curl http://localhost:8080/api/tasks


### タスク作成(POST)
- curl -X POST http://localhost:8080/api/tasks \
 -H "Content-Type: application/json" \
 -d '{"title":"買い物"}'


### タスク更新(PUT)
- curl -X PUT http://localhost:8080/api/tasks/1 \
 -H "Content-Type: application/json" \
 -d '{"title":"買い物(更新)","completed":true}'
 

###  タスク削除
-curl -X DELETE http://localhost:8080/api/tasks/1


## 例外ハンドリングの動作例
### 存在しないIDを指定した場合（404）
- curl http://localhost:8080/api/tasks/9999


- {"error":"Task not found"}


### バリデーションエラー（400）
- curl -X POST http://localhost:8080/api/tasks \
 -H "Content-Type: application/json" \
 -d '{"title":""}'


{
  "error": "Validation failed",
  "details": {
    "title": "タイトルを入力してください"
  }
}


## 発展課題

- クエリパラメータ（sort / direction）を指定することで、タスク一覧の並び順を動的に変更できるようにしました。


- curl "http://localhost:8080/api/tasks?sort=createdAt&direction=desc"
