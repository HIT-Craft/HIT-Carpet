# HIT Carpet

一个经过魔改的 [1.14.4 carpet mod](https://github.com/gnembon/fabric-carpet)

# carpet的命令设置
原版carpet指令请参考[wiki](https://github.com/gnembon/fabric-carpet/wiki/Current-Available-Settings)或[汉化版本](https://github.com/SFG-Minecraft-mods-Chinese-Translation/Translation-doc/blob/master/Fabric-Carpet/Commands.md)

# 新增或调整功能
## 监视器(log)
### 方块实体(blockentity)
`/log blockentity <监视场景>`

记录方块实体信息
* 目前仅支持末地石农场设计所需的方块实体添加信息与末地折跃门相关信息
* 默认值: `endislandfarm`
* 可选参数: `endislandfarm`

### 加载票(ticket)
`/log ticket <监视类型>`

记录加载票的添加与移除
* 默认值: `portal`
* 可选参数: `portal,player`, `portal,dragon`, `start`, `dragon`, `player`, `forced`, `light`, `portal`, `post_teleport`, `unknown`

**警告:** 监视 `unknown` 加载票可能会导致刷屏

## 光照更新(lightUpdates)
关闭以关闭服务器光照更新
* 默认值: `true`
* 可选参数: `true`, `false`
* 关闭方法: `/carpet lightUpdates false`

**警告：** 请勿设置默认关闭，否则服务器将无法启动

**警告：** 关闭光照抑制时无法加载新区块，请提前加载区块，请勿在未加载区块上线

## 地狱门缓存(portalSuperCache)
减少在1.14中实体经过地狱门而产生的卡顿
* 默认值: `false`
* 可选参数: `true`, `false`
* 开启方法: `/carpet portalSuperCache true`

**警告：** 非正常手段放置或破坏地狱门方块可能会导致无法建立缓存

## 合并TNT(mergeTNT)
合并大量TNT以减小实体及爆炸带来的卡顿
* 默认值: `false`
* 可选参数: `true`, `false`
* 开启方法: `/carpet mergeTNT true`

# Works Cited
- Fallen_Breath [Carpet-TIS-Addition](https://github.com/TISUnion/Carpet-TIS-Addition)
- LucunJi [fabric-carpet](https://github.com/LucunJi/fabric-carpet)
