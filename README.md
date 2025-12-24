# JavaTraining Week4 - Spring Boot REST API

Spring Boot を用いて、最小構成の REST API を作成する課題です。
Hello API と簡単なタスク管理 API を実装しました。

---

## 開発環境

- Java: OpenJDK 21 (Temurin 21.0.9)
- Spring Boot: 3.5.9
- ビルドツール: Gradle 8.14.3(Gradle Wrapper 使用)
- IDE: Visual Studio Code
- OS: Windows

## セットアップ手順

### リポジトリのクローン

- git clone <https://github.com/k-hmmt011923/JavaTraining-Week4.git>

- cd JavaTraining-Week4

## アプリケーションの起動

# Gradle を使用

- ./gradlew bootRun

- gradlew.bat bootRun (上記で実行できない場合)

## 動作確認手順

# Hello API(ブラウザで確認)

- http://localhost:8080/hello

-動作確認例-

Hello, Spring Boot!

#　タスク作成(CLI で実行)

# POST

curl -X POST -H "Content-Type: application/json" -d '{"title":"タイトル"}' http://localhost:8080/api/tasks

#　タスク一覧取得(CLI で実行)

# GET

curl http://localhost:8080/api/tasks

[
{"id":1,"title":"タイトル"}
]

# 起動確認（Actuator）

http://localhost:8080/actuator/health

-動作確認例-

{"status":"UP"}

## エラー発生時の対処

## エラー発生時の対処

- PowerShell で curl を用いて POST リクエストを送信した際、JSON の記述方法が正しくない場合に 400 Bad Request エラーが発生しました。

- リクエストボディをシングルクォートで囲む形式に修正して、正しい JSON として送信することで処理が正常に行われること確認しました。
