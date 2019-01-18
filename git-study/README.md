* 過去の履歴消して無理やり push
```
git reset --hard xxxx
git push -f
```

* 過去の履歴を残して戻す
```
git revert xxxx
git push
```

* Git での履歴追跡について
https://stackoverflow.com/questions/2314652/is-it-possible-to-move-rename-files-in-git-and-maintain-their-history
https://git.wiki.kernel.org/index.php/GitFaq#Why_does_Git_not_.22track.22_renames.3F

```
git log --follow `filename` で追えって言ってる
```

色々試したが、無理っぽい。。。？
